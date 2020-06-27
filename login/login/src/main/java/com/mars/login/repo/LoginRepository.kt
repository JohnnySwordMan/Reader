package com.mars.login.repo

import com.mars.common_base.response.BaseResponse
import com.mars.common_base.user.model.User
import com.mars.login.api.LoginApi
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class LoginRepository(private val api: LoginApi) : ILoginRepository {


    override fun register(
        username: String,
        password: String,
        repassword: String
    ): Observable<BaseResponse<User>> {
        return api.register(username, password, repassword)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }


    override fun login(username: String, password: String): Observable<BaseResponse<User>> {
        return api.login(username, password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }


    override fun logout(): Observable<BaseResponse<Any>> {
        return api.logout()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

}