package com.mars.reader.account

import com.mars.core.response.SUCCESS
import com.mars.user.manager.UserManager
import com.mars.reader.login.api.LoginService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object AccountManager {


    fun isLogin() = UserManager.currentUser != null


    fun logout() = LoginService.logout()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .doOnNext {
            if (it.errorCode == SUCCESS) {
                UserManager.notifyLogout()
            }
        }
}