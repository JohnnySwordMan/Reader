package com.mars.reader.knowledge.api

import com.mars.core.response.BaseResponse
import com.mars.reader.knowledge.model.ChapterData
import com.mars.reader.knowledge.model.KnowledgeNavigationData
import com.mars.reader.home.model.ArticlePage
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface KnowledgeApi {


    @GET("tree/json")
    fun getChapterList(): Observable<BaseResponse<List<ChapterData>>>

    @GET("navi/json")
    fun getKnowledgeNaviList(): Observable<BaseResponse<List<KnowledgeNavigationData>>>

    @GET("article/list/{page}/json")
    fun getKnowledgeArticlesByChildrenId(@Path("page") page: Int, @Query("cid") cid: Int): Observable<BaseResponse<ArticlePage>>
}