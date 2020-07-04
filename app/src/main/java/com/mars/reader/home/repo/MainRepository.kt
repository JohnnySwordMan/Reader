package com.mars.reader.home.repo

import com.mars.core.utils.RxUtil
import com.mars.reader.home.api.MainApi
import com.mars.reader.home.model.ArticlePage
import io.reactivex.Observable

class MainRepository(private val mainApi: MainApi) {


    fun getArticlePages(pageIndex: Int): Observable<ArticlePage> {
        return mainApi.getArticlePages(pageIndex)
            .compose(RxUtil.applySchedulers())
            .compose(RxUtil.handleResult())
    }
}