package com.mars.core.response

import com.mars.core.anno.PoKo

@PoKo
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