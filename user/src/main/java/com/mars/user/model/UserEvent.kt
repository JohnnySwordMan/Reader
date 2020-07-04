package com.mars.user.model


class UserEvent(private val user: User?, @UserStatus private val status: Int) {

    fun isLogin() = status == UserStatus.LOGIN

    fun isLogout() = status == UserStatus.LOGOUT

    fun isUpdate() = status == UserStatus.UPDATE


}