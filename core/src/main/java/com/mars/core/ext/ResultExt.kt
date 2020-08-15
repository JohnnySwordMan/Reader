package com.mars.core.ext

import com.mars.core.model.Result

/**
 * @description:
 * @author: geyan
 * @date: 2020-08-15
 */
inline fun <T : Any> Result<T>.checkResult(
    crossinline onSuccess: (T) -> Unit,
    crossinline onError: (String?) -> Unit
) {
    if (this is Result.Success) {
        onSuccess(data)
    } else if (this is Result.Error) {
        onError(exception.message)
    }
}