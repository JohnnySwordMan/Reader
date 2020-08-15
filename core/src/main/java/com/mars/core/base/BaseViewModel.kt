package com.mars.core.base

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * @description: update with coroutines
 * @author: geyan
 * @date: 2020-08-15
 *
 * 参考：https://developer.android.com/topic/libraries/architecture/coroutines?hl=zh-cn
 * 为应用中的每个 ViewModel 定义了 ViewModelScope。
 * 如果 ViewModel 已清除，则在此范围内启动的协程都会自动取消。
 * 如果您具有仅在 ViewModel 处于活动状态时才需要完成的工作，此时协程非常有用。
 * 例如，如果要为布局计算某些数据，则应将工作范围限定至 ViewModel，以便在 ViewModel 清除后，系统会自动取消工作以避免消耗资
 *
 * 之前使用RxViewModel，还需要在onCleared方法中，处理disposable对象
 */
open class BaseViewModel : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    // ViewModel被销毁时，会调用
    override fun onCleared() {
        compositeDisposable.clear()
    }

    @Deprecated(
        "this function is deprecated. And recommend use launchOnUI",
        ReplaceWith("launchOnUI(block: suspend CoroutineScope.() -> Unit)"),
        DeprecationLevel.WARNING
    )
    fun register(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    fun launchOnUI(block: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch {
            Log.e("gy", "BaseViewModel---launchOnUI---current thread = ${Thread.currentThread().name}")
            block()
        }
    }

    /**
     * 定义UI层和ViewModel进行交互时，UI的状态
     */
    open class UiState<T>(
        val isSuccess: T? = null,
        val isError: String? = null,
        val isLoading: Boolean = false,
        val isRefresh: Boolean = false
    )
}