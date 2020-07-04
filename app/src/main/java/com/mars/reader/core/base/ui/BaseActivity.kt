package com.mars.reader.core.base.ui

import android.os.Bundle
import android.view.WindowManager
import com.mars.reader.R
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BaseActivity : DaggerAppCompatActivity() {


    private val compositeDisposable = CompositeDisposable()

    protected fun register(subscription: Disposable) {
        compositeDisposable.add(subscription)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        // 沉浸式布局
        if (canImmersiveStyle()) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.statusBarColor = resources.getColor(R.color.colorPrimary)
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }

    /**
     * 是否使用沉浸式布局
     */
     open fun canImmersiveStyle(): Boolean = false

}