package com.mars.reader.login.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mars.base.util.Logger
import com.mars.core.ab.CoroutinesManager
import com.mars.core.exception.ApiErrorCode
import com.mars.core.exception.ApiException
import com.mars.core.ext.log
import com.mars.core.response.ErrorData
import com.mars.core.base.BaseViewModel
import com.mars.core.ext.checkResult
import com.mars.core.model.Result
import com.mars.reader.login.model.LoginUiState
import com.mars.reader.login.repo.ILoginRepository
import com.mars.user.manager.UserManager
import com.mars.reader.login.repo.LoginRepository
import com.mars.user.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * @Tips
 * private val errorResult = MutableLiveData<Pair<Int, String>>() 到 private val errorResult = MutableLiveData<ErrorData>()，
 * 已经意识到Pair存在的问题，如果返回的参数增加了，就会难以处理，逻辑混乱；其次，一直在纠结服务端返回的错误数据是放到loginResult中，还是errorResult中，
 * 因为部分场景中，可能有后续的逻辑。没法将错误收敛到一个result中
 */
class LoginViewModel(private val repo: ILoginRepository) : BaseViewModel() {

    private val loginResult = MutableLiveData<Boolean>()

    //    private val errorResult = MutableLiveData<Pair<Int, String>>()
    fun getLoginResult(): LiveData<Boolean> = loginResult

    //    fun getErrorResult(): LiveData<Pair<Int, String>> = errorResult
    private val errorResult = MutableLiveData<ErrorData>()
    fun getErrorResult(): LiveData<ErrorData> {
        return errorResult
    }

    private val uiState = MutableLiveData<LoginUiState<User>>()

    fun getUiState(): LiveData<LoginUiState<User>> {
        return uiState
    }

    fun login(username: String?, password: String?) {
        log("username = $username, password = $password")
        if (CoroutinesManager.useCoroutines) {
            loginWithCoroutines(username, password)
        } else {
            register(repo.login(username!!, password!!).subscribe({
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
    }

    /**
     * 1. 判断
     * 2. 请求
     */
    private fun loginWithCoroutines(username: String?, password: String?) {
        launchOnUI {
            if (username.isNullOrEmpty() || password.isNullOrEmpty()) {
                uiState.value = LoginUiState(enableBtn = false)
                return@launchOnUI
            }
            uiState.value = LoginUiState(isLoading = true)
            val result = repo.loginV2(username, password)
            Logger.e(
                "gy",
                "LoginRepository---result = $result, current thread = ${Thread.currentThread().name}"
            )
            // 这个跟下面的checkResult作用是一样的，但是checkResult更加kotlin化
            if (result is Result.Success) {

            } else if (result is Result.Error) {

            }
            result.checkResult(
                onSuccess = {
                    UserManager.notifyLogin(it)
                    uiState.value = LoginUiState(isSuccess = it)
                },
                onError = {
                    uiState.value = LoginUiState(isError = it)
                }
            )
        }
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