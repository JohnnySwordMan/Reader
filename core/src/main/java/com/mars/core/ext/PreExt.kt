package com.mars.core.ext

import com.mars.core.AppContext
import com.mars.core.utils.Preference
import kotlin.reflect.jvm.jvmName

inline fun <reified R, T> R.pref(default: T) = Preference(AppContext, "", default, R::class.jvmName)