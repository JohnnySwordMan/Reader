package com.mars.reader.login.api

import com.mars.core.response.BaseResponse
import com.mars.core.services.retrofit
import com.mars.user.model.User
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface LoginApi {

    @FormUrlEncoded
    @POST("user/login")
    fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): Observable<BaseResponse<User>>


    @GET("user/logout/json")
    fun logout(): Observable<BaseResponse<Any>>
}

object LoginService : LoginApi by retrofit.create(LoginApi::class.java)