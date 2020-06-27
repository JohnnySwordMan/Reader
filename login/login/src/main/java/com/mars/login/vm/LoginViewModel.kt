package com.mars.login.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mars.common_base.response.ERROR
import com.mars.common_base.response.ErrorData
import com.mars.common_base.user.manager.UserManager
import com.mars.common_base.response.SUCCESS
import com.mars.common_base.rx.RxViewModel
import com.mars.common_base.user.model.User
import com.mars.login.LoginFragment.Companion.TAG
import com.mars.login.repo.ILoginRepository
import com.mars.util.ext.Logger

class LoginViewModel(private val loginRepo: ILoginRepository) : RxViewModel() {

    private val loginResult = MutableLiveData<User>()

    fun getLoginResult(): LiveData<User> {
        return loginResult
    }

    private val errorResult = MutableLiveData<ErrorData>()

    fun getErrorResult(): LiveData<ErrorData> {
        return errorResult
    }

    fun register(username: String, password: String, repassword: String) {
        register(
            loginRepo.register(username, password, repassword)
                .subscribe({

                }, {

                })
        )
    }

    fun login(username: String, password: String) {
        register(
            loginRepo.login(username, password)
                .subscribe({
                    if (it.errorCode == SUCCESS) {
                        Logger.e(TAG, "user = ${it.data}")
                        loginResult.value = it.data
                        // 保存user
                        UserManager.notifyLogin(it.data)
                    } else {
                        Logger.e(TAG, "errorMsg = ${it.errorMsg}")
                        errorResult.value = ErrorData(it.errorCode, it.errorMsg)
                    }
                }, {
                    Logger.e(TAG, "throwable = $it")
                    errorResult.value = ErrorData(ERROR, it.toString())
                })
        )
    }

    fun logout() {
        register(
            loginRepo.logout()
                .subscribe({

                }, {

                })
        )
    }
}