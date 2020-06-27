package com.mars.login.repo

import com.mars.common_base.response.BaseResponse
import com.mars.common_base.user.model.User
import io.reactivex.Observable

interface ILoginRepository {

    fun register(
        username: String,
        password: String,
        repassword: String
    ): Observable<BaseResponse<User>>

    fun login(username: String, password: String): Observable<BaseResponse<User>>


    fun logout(): Observable<BaseResponse<Any>>
}