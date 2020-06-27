package com.mars.common_base.response


data class BaseResponse<T>(
    var data: T,
    var errorCode: Int = 0,
    var errorMsg: String
)

data class ErrorData(
    val errorCode: Int,
    val errorMsg: String
)

const val SUCCESS = 0
const val ERROR = -1

