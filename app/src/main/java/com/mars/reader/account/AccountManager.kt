package com.mars.reader.account

import com.mars.common_base.response.BaseResponse
import com.mars.common_base.response.SUCCESS
import com.mars.common_base.service.retrofit
import com.mars.common_base.user.manager.UserManager
import com.mars.login.api.LoginApi
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

interface IAccountManager {

    fun isLogin(): Boolean

    fun logout(): Observable<BaseResponse<Any>>
}

object AccountManager : IAccountManager {

    override fun isLogin() = UserManager.currentUser != null


    override fun logout() = LoginService.logout()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .doOnNext {
            // 常用do操作符，先做一些操作，外界无须关心的
            if (it.errorCode == SUCCESS) {
                UserManager.notifyLogout()
            }
        }
}

object LoginService : LoginApi by retrofit.create(LoginApi::class.java)