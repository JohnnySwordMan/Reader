package com.mars.login.api

import com.mars.common_base.response.BaseResponse
import com.mars.common_base.user.model.User
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface LoginApi {

    @FormUrlEncoded
    @POST("user/register")
    fun register(
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("repassword") repassword: String
    ): Observable<BaseResponse<User>>


    @FormUrlEncoded
    @POST("user/login")
    fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): Observable<BaseResponse<User>>
    

    @GET("user/logout/json")
    fun logout(): Observable<BaseResponse<Any>>
}