package com.mars.common_base.user.manager

import com.mars.common_base.user.model.UserEvent
import com.abyte.user.model.UserStatus
import com.google.gson.Gson
import com.mars.common_base.ext.fromJson
import com.mars.common_base.ext.pref
import com.mars.common_base.user.model.User
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

object UserManager : IUserManager {

    private val publishUserStatus = PublishSubject.create<UserEvent>()

    private var userStr by pref("")

    // 外界可以直接获取
    var currentUser: User? = null
        get() {
            if (field == null && userStr.isNotEmpty()) {
                field = Gson().fromJson<User>(userStr)
            }
            return field
        }
        set(value) {
            userStr = if (value == null) "" else Gson().toJson(value)
            field = value
        }


    override fun notifyLogin(user: User) {
        currentUser = user
        publishUserStatus.onNext(UserEvent(user, UserStatus.LOGIN))
    }

    override fun notifyLogout() {
        currentUser = null
        publishUserStatus.onNext(UserEvent(null, UserStatus.LOGOUT))
    }


    override fun observerLoginStatus(): Observable<UserEvent> = publishUserStatus


}

