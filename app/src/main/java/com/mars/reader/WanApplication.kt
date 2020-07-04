package com.mars.reader

import android.app.Application
import com.mars.core.AppContextUtil
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

private lateinit var INSTANCE: Application

class WanApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerWanComponent.builder().application(this).build()
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        AppContextUtil.setContext(this)
    }
}