package com.mars.common_base.util

import android.app.Application
import android.content.ContextWrapper

object AppContextUtil {

    lateinit var mContext: Application

    fun setContext(context: Application) {
        this.mContext = context
    }
}

object AppContext: ContextWrapper(AppContextUtil.mContext)