package com.mars.reader

import android.app.Application
import com.mars.common_base.util.AppContextUtil
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

private lateinit var INSTANCE: Application

class ReaderApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerReaderComponent.builder().application(this).build()
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        AppContextUtil.setContext(this)
    }
}