package com.mars.util.ext

import android.util.Log

object Logger {

    fun e(tag: String, msg: Any?) {
        Log.e(tag, "$msg")
    }
}