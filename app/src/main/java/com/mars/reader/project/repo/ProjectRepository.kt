package com.mars.reader.project.repo

import com.mars.core.utils.RxUtil
import com.mars.reader.knowledge.model.ChapterData
import com.mars.reader.home.model.ArticlePage
import com.mars.reader.project.api.ProjectApi
import io.reactivex.Observable

class ProjectRepository(private val api: ProjectApi) {

    fun getProjectTabs(): Observable<List<ChapterData>> {
        return api.getProjectTabs()
            .compose(RxUtil.applySchedulers())
            .compose(RxUtil.handleResult())
    }

    fun getProjectArticlesById(page: Int, cid: Int): Observable<ArticlePage> {
        return api.getProjectArticlesById(page, cid)
            .compose(RxUtil.applySchedulers())
            .compose(RxUtil.handleResult())
    }
}