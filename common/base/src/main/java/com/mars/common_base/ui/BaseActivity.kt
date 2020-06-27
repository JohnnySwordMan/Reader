package com.mars.common_base.ui

import androidx.appcompat.app.AppCompatActivity
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BaseActivity : DaggerAppCompatActivity() {

    private val compositeDisposable = CompositeDisposable()

    protected fun register(subscription: Disposable) {
        compositeDisposable.add(subscription)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}