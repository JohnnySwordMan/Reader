package com.mars.reader.wechat.repo

import com.mars.core.utils.RxUtil
import com.mars.reader.knowledge.model.ChapterData
import com.mars.reader.home.model.ArticlePage
import com.mars.reader.wechat.api.WechatApi
import io.reactivex.Observable

class WechatRepository(private val api: WechatApi) {

    fun getWechatArticles(): Observable<List<ChapterData>> {
        return api.getWechatArticles()
            .compose(RxUtil.applySchedulers())
            .compose(RxUtil.handleResult())
    }

    fun getWechatArticlesOfAuthor(authorId: Int, page: Int): Observable<ArticlePage> {
        return api.getWechatArticlesOfAuthor(authorId, page)
            .compose(RxUtil.applySchedulers())
            .compose(RxUtil.handleResult())
    }
}