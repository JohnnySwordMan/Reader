package com.mars.reader.login.model

import com.mars.core.base.BaseViewModel

/**
 * @description: 定义登录场景下的Ui状态
 * @author: geyan
 * @date: 2020-08-15
 */
class LoginUiState<T>(
    isSuccess: T? = null,
    isError: String? = null,
    isLoading: Boolean = false,
    val enableBtn: Boolean = false
) : BaseViewModel.UiState<T>(isSuccess, isError, isLoading, false)