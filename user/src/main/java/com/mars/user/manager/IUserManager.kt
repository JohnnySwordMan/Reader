package com.mars.user.manager

import com.mars.user.model.User
import com.mars.user.model.UserEvent
import io.reactivex.Observable

interface IUserManager {

    fun notifyLogin(user: User)

    fun notifyLogout()

//    fun observerLogin(): Observable<User>

    fun observerLoginStatus(): Observable<UserEvent>

}