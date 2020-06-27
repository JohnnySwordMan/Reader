package com.mars.common_base.ext

import com.mars.common_base.Preference
import com.mars.common_base.util.AppContext
import kotlin.reflect.jvm.jvmName

inline fun <reified R, T> R.pref(default: T) = Preference(AppContext, "", default, R::class.jvmName)