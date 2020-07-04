package com.mars.reader.wechat.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mars.core.exception.ApiErrorCode
import com.mars.core.exception.ApiException
import com.mars.core.rx.RxViewModel
import com.mars.reader.knowledge.model.ChapterData
import com.mars.reader.home.model.ArticlePage
import com.mars.reader.wechat.repo.WechatRepository

class WechatViewModel(private val repo: WechatRepository) : RxViewModel() {


    private var wechatArticlesResult = MutableLiveData<List<ChapterData>>()

    private var wechatAuthorArticlesResult = MutableLiveData<ArticlePage>()

    private var errorResult = MutableLiveData<Pair<Int, String>>()

    fun getWechatArticlesResult(): LiveData<List<ChapterData>> {
        return wechatArticlesResult
    }

    fun getWechatAuthorArticlesResult(): LiveData<ArticlePage> {
        return wechatAuthorArticlesResult
    }

    fun getErrorResult(): LiveData<Pair<Int, String>> {
        return errorResult
    }

    fun getWechatArticles() {
        register(repo.getWechatArticles().subscribe({
            it?.let {
                wechatArticlesResult.value = it
            } ?: run {
                errorResult.value = Pair(ApiErrorCode.ERROR_DATA_EMPTY, "数据为空")
            }
        }, {
            val exception = it as ApiException
            errorResult.value = Pair(exception.code, exception.msg ?: "请求失败")
        }))
    }

    fun getWechatArticlesOfAuthor(authorId: Int, page: Int) {
        register(repo.getWechatArticlesOfAuthor(authorId, page).subscribe({
            it?.let {
                wechatAuthorArticlesResult.value = it
            } ?: run {
                errorResult.value = Pair(ApiErrorCode.ERROR_DATA_EMPTY, "数据为空")
            }
        }, {
            val exception = it as ApiException
            errorResult.value = Pair(exception.code, exception.msg ?: "请求失败")
        }))
    }

    fun refreshData(authorId: Int) {
        getWechatArticlesOfAuthor(authorId, 1)
    }

    fun loadMoreData(authorId: Int, nextPage: Int) {
        getWechatArticlesOfAuthor(authorId, nextPage)
    }

}