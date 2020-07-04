package com.mars.reader.knowledge.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mars.core.exception.ApiErrorCode
import com.mars.core.exception.ApiException
import com.mars.core.rx.RxViewModel
import com.mars.reader.knowledge.model.ChapterData
import com.mars.reader.knowledge.model.KnowledgeNavigationData
import com.mars.reader.knowledge.repo.KnowledgeRepository
import com.mars.reader.home.model.ArticlePage

class KnowledgeViewModel(val repo: KnowledgeRepository) : RxViewModel() {

    private var chapterDataResult = MutableLiveData<List<ChapterData>>()

    private var knowledgeNaviDataResult = MutableLiveData<List<KnowledgeNavigationData>>()

    private var knowledgeArticlesResult = MutableLiveData<ArticlePage>()

    private var errorResult = MutableLiveData<Pair<Int, String>>()

    fun getChapterDataResult(): LiveData<List<ChapterData>> {
        return chapterDataResult
    }

    fun getKnowledgeNaviDataResult(): LiveData<List<KnowledgeNavigationData>> {
        return knowledgeNaviDataResult
    }

    fun getKnowledgeArticlesResult(): LiveData<ArticlePage> {
        return knowledgeArticlesResult
    }

    fun getErrorResult(): LiveData<Pair<Int, String>> {
        return errorResult
    }

    fun getChapterList() {
        register(repo.getChapterList().subscribe({
            it?.let {
                chapterDataResult.value = it
            } ?: run {
                errorResult.value = Pair(ApiErrorCode.ERROR_DATA_EMPTY, "数据为空")
            }
        }, {
            val exception = it as ApiException
            errorResult.value = Pair(exception.code, exception.msg ?: "请求失败")
        }))
    }

    fun getKnowledgeNaviList() {
        register(repo.getKnowledgeNaviList().subscribe({
            it?.let {
                knowledgeNaviDataResult.value = it
            } ?: run {
                errorResult.value = Pair(ApiErrorCode.ERROR_DATA_EMPTY, "数据为空")
            }
        }, {
            val exception = it as ApiException
            errorResult.value = Pair(exception.code, exception.msg ?: "请求失败")
        }))
    }

    fun getKnowledgeArticlesByChildrenId(page: Int, cid: Int) {
        register(repo.getKnowledgeArticlesByChildrenId(page, cid).subscribe({
            it?.let {
                knowledgeArticlesResult.value = it
            } ?: run {
                errorResult.value = Pair(ApiErrorCode.ERROR_DATA_EMPTY, "数据为空")
            }
        }, {
            val exception = it as ApiException
            errorResult.value = Pair(exception.code, exception.msg ?: "请求失败")
        }))
    }

    fun refreshData(cid: Int) {
        getKnowledgeArticlesByChildrenId(0, cid)
    }

    fun loadMoreData(page: Int, cid: Int) {
        getKnowledgeArticlesByChildrenId(page, cid)
    }
}