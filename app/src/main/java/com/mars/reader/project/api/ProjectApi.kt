package com.mars.reader.project.api

import com.mars.core.response.BaseResponse
import com.mars.reader.knowledge.model.ChapterData
import com.mars.reader.home.model.ArticlePage
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProjectApi {

    @GET("project/tree/json")
    fun getProjectTabs(): Observable<BaseResponse<List<ChapterData>>>

    // Path和Query的区别，project/list/1/json?cid=294
    @GET("project/list/{page}/json")
    fun getProjectArticlesById(@Path("page") page: Int, @Query("cid") cid: Int): Observable<BaseResponse<ArticlePage>>
}