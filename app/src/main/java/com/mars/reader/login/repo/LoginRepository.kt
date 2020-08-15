package com.mars.reader.login.repo

import com.mars.base.util.Logger
import com.mars.core.base.BaseRepository
import com.mars.core.model.Result
import com.mars.core.utils.RxUtil
import com.mars.user.model.User
import com.mars.reader.login.api.LoginApi
import com.mars.user.manager.UserManager
import io.reactivex.Observable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * 面向接口，后续替换LoginRepository，或者对外提供，只需要实现该接口即可
 */
interface ILoginRepository {

    @Deprecated("this func is deprecated")
    fun login(username: String, password: String): Observable<User>

    suspend fun loginV2(username: String, password: String): Result<User>
}

class LoginRepository(private val loginApi: LoginApi) : BaseRepository(), ILoginRepository {

    override fun login(username: String, password: String): Observable<User> {
        return loginApi.login(username, password)
            .compose(RxUtil.applySchedulers())
            .compose(RxUtil.handleResult())
    }

    override suspend fun loginV2(username: String, password: String): Result<User> {
        return withContext(Dispatchers.IO) {
            safeApiCall { requestLogin(username, password) }
        }
    }

    private suspend fun requestLogin(username: String, password: String): Result<User> {
        val response = loginApi.loginV2(username, password)
        // 这里也是ui线程
        Logger.e("gy", "requestLogin---current thread = ${Thread.currentThread().name}")
        return executeResponse(response, {
            // 内部处理response，而ViewModel中，则更多的是将返回值赋值给LiveData，进行ui层的刷新
            val user = response.data
            // 不能在这里notifyLogin，需要在主线程中notifyLogin
//            UserManager.notifyLogin(user)
        })
    }

}