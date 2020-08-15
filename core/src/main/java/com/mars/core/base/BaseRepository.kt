package com.mars.core.base

import com.mars.core.model.Result
import com.mars.core.response.BaseResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope

/**
 * @description: Repository基类，进行数据处理
 * @author: geyan
 * @date: 2020-08-15
 */
open class BaseRepository {

    /**
     * coroutineScope结构化并发：1. 只有CoroutineScope表达式中的所有任务都完成，才会返回；2. 如果外界的任务取消了，那么子协程中的任务也就取消了
     */
    suspend fun <T : Any> executeResponse(
        response: BaseResponse<T>,
        successBlock: (suspend CoroutineScope.() -> Unit)? = null,
        errorBlock: (suspend CoroutineScope.() -> Unit)? = null
    ): Result<T> {
        return coroutineScope {
            if (response.errorCode == -1) {
                errorBlock?.let {
                    it()
                }
                Result.Error(Exception(response.errorMsg))
            } else {
                successBlock?.let {
                    it()
                }
                Result.Success(response.data)
            }
        }
    }

    suspend fun <T : Any> safeApiCall(call: suspend () -> Result<T>): Result<T> {
        return try {
            call()
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

}