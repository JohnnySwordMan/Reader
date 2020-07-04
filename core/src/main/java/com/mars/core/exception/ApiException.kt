package com.mars.core.exception

data class ApiException(val code: Int, val msg: String?) : Exception()