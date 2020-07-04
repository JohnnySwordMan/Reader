package com.mars.reader.wechat.api

import com.mars.core.response.BaseResponse
import com.mars.reader.knowledge.model.ChapterData
import com.mars.reader.home.model.ArticlePage
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface WechatApi {

    @GET("wxarticle/chapters/json")
    fun getWechatArticles(): Observable<BaseResponse<List<ChapterData>>>

    @GET("wxarticle/list/{authorId}/{page}/json")
    fun getWechatArticlesOfAuthor(@Path("authorId") authorId: Int, @Path("page") page: Int):
            Observable<BaseResponse<ArticlePage>>
}