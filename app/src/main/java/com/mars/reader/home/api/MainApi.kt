package com.mars.reader.home.api

import com.mars.core.response.BaseResponse
import com.mars.reader.home.model.ArticlePage
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface MainApi {


    /**
     * 首页文章列表
     * 方法：GET
     * 参数：页码，拼接在连接中，从0开始。
     */
    @GET("article/list/{page}/json")
    fun getArticlePages(@Path("page") page: Int): Observable<BaseResponse<ArticlePage>>
}