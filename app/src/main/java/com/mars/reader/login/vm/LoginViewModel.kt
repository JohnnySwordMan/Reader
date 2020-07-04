package com.mars.reader.login.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mars.core.exception.ApiErrorCode
import com.mars.core.exception.ApiException
import com.mars.core.ext.log
import com.mars.core.response.ErrorData
import com.mars.core.rx.RxViewModel
import com.mars.user.manager.UserManager
import com.mars.reader.login.repo.LoginRepository

class LoginViewModel(private val repo: LoginRepository) : RxViewModel() {

    private val loginResult = MutableLiveData<Boolean>()

//    private val errorResult = MutableLiveData<Pair<Int, String>>()

    fun getLoginResult(): LiveData<Boolean> = loginResult

//    fun getErrorResult(): LiveData<Pair<Int, String>> = errorResult

    private val errorResult = MutableLiveData<ErrorData>()

    fun getErrorResult(): LiveData<ErrorData> {
        return errorResult
    }

    fun login(username: String, password: String) {
        log("username = $username, password = $password")
        register(repo.login(username, password).subscribe({
            log("login---success---username = ${it.username}")
            loginResult.value = true
            UserManager.notifyLogin(it)
            // 保存
        }, {
            val exception = it as ApiException
            log("login---fail = $exception")
            errorResult.value = ErrorData(exception.code, exception.msg ?: "登录失败")
        }))
    }


    fun checkUsernameAndPassword(username: String?, password: String?): Boolean {
        return check(!username.isNullOrEmpty() && !password.isNullOrEmpty()) {
            "用户名或密码错误"
        }
    }


    private fun check(value: Boolean, lazyMessage: () -> Any): Boolean {
        if (!value) {
            val message = lazyMessage()
            errorResult.value =
                ErrorData(ApiErrorCode.ERROR_USERNAME_OR_PASSWORD, message.toString())
        }
        return value
    }
}