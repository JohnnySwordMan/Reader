package com.mars.core.model

/**
 * @description: 网络请求的结果，对于一个网络请求来说，只有成功和失败两种结果
 * @author: geyan
 * @date: 2020-08-15
 */
sealed class Result<out T : Any> {
    data class Success<out T : Any>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success -> "Success[data = $data]"
            is Error -> "Error[exception = $exception]"
        }
    }
}


