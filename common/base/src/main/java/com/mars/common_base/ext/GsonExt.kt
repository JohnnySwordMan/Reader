package com.mars.common_base.ext

import com.google.gson.Gson


inline fun <reified T> Gson.fromJson(json: String) = fromJson(json, T::class.java)