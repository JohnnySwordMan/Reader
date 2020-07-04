package com.mars.reader.rank.api

import com.mars.core.response.BaseResponse
import com.mars.reader.rank.model.RankPage
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface RankApi {

    @GET("coin/rank/{page}/json")
    fun getRankList(@Path("page") page: Int): Observable<BaseResponse<RankPage>>
}