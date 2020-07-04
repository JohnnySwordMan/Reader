package com.mars.reader.login.repo

import com.mars.core.utils.RxUtil
import com.mars.user.model.User
import com.mars.reader.login.api.LoginApi
import io.reactivex.Observable

class LoginRepository(private val loginApi: LoginApi) {

    fun login(username: String, password: String): Observable<User> {
        return loginApi.login(username, password)
            .compose(RxUtil.applySchedulers())
            .compose(RxUtil.handleResult())
    }
}