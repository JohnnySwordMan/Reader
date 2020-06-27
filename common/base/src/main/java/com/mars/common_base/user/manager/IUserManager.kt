package com.mars.common_base.user.manager

import com.mars.common_base.user.model.UserEvent
import com.mars.common_base.user.model.User
import io.reactivex.Observable

interface IUserManager {

    fun notifyLogin(user: User)

    fun notifyLogout()

//    fun observerLogin(): Observable<User>

    fun observerLoginStatus(): Observable<UserEvent>

}