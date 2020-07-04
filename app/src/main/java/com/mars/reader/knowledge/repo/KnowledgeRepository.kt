package com.mars.reader.knowledge.repo

import com.mars.core.utils.RxUtil
import com.mars.reader.knowledge.api.KnowledgeApi
import com.mars.reader.knowledge.model.ChapterData
import com.mars.reader.knowledge.model.KnowledgeNavigationData
import com.mars.reader.home.model.ArticlePage
import io.reactivex.Observable

class KnowledgeRepository(private val api: KnowledgeApi) {

    fun getChapterList(): Observable<List<ChapterData>> {
        return api.getChapterList()
            .compose(RxUtil.applySchedulers())
            .compose(RxUtil.handleResult())
    }


    fun getKnowledgeNaviList(): Observable<List<KnowledgeNavigationData>> {
        return api.getKnowledgeNaviList()
            .compose(RxUtil.applySchedulers())
            .compose(RxUtil.handleResult())
    }

    fun getKnowledgeArticlesByChildrenId(page: Int, cid: Int): Observable<ArticlePage> {
        return api.getKnowledgeArticlesByChildrenId(page, cid)
            .compose(RxUtil.applySchedulers())
            .compose(RxUtil.handleResult())
    }
}