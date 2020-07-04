package com.mars.reader.project.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mars.core.exception.ApiErrorCode
import com.mars.core.exception.ApiException
import com.mars.core.rx.RxViewModel
import com.mars.reader.knowledge.model.ChapterData
import com.mars.reader.home.model.ArticlePage
import com.mars.reader.project.repo.ProjectRepository

class ProjectViewModel(private val repo: ProjectRepository) : RxViewModel() {


    private var projectTabsResult = MutableLiveData<List<ChapterData>>()

    private var projectArticlesResult = MutableLiveData<ArticlePage>()

    private var errorResult = MutableLiveData<Pair<Int, String>>()

    fun getProjectTabsResult(): LiveData<List<ChapterData>> {
        return projectTabsResult
    }

    fun getProjectArticlesResult(): LiveData<ArticlePage> {
        return projectArticlesResult
    }

    fun getErrorResult(): LiveData<Pair<Int, String>> {
        return errorResult
    }

    fun getProjectTabs() {
        register(repo.getProjectTabs().subscribe({
            it?.let {
                projectTabsResult.value = it
            } ?: run {
                errorResult.value = Pair(ApiErrorCode.ERROR_DATA_EMPTY, "数据为空")
            }
        }, {
            val exception = it as ApiException
            errorResult.value = Pair(exception.code, exception.msg ?: "请求失败")
        }))
    }

    fun getProjectArticlesById(page: Int, cid: Int) {
        register(repo.getProjectArticlesById(page, cid).subscribe({
            it?.let {
                projectArticlesResult.value = it
            } ?: run {
                errorResult.value = Pair(ApiErrorCode.ERROR_DATA_EMPTY, "数据为空")
            }
        }, {
            val exception = it as ApiException
            errorResult.value = Pair(exception.code, exception.msg ?: "请求失败")
        }))
    }

    fun refreshData(cid: Int) {
        getProjectArticlesById(1, cid)
    }

    fun loadMoreData(nextPage: Int, cid: Int) {
        getProjectArticlesById(nextPage, cid)
    }
}