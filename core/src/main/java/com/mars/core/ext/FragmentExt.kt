package com.mars.core.ext

import androidx.fragment.app.FragmentTransaction


fun runSafely(beginTransaction: FragmentTransaction, block: () -> Unit) {
    try {
        block()
    } catch (e: Exception) {
        try {
            beginTransaction.commitAllowingStateLoss()
        } catch (e1: Exception) {
            e1.printStackTrace()
        }
    }
}

fun FragmentTransaction.runSafelyV2(block: () -> Unit) {
    try {
        block()
    } catch (e: Exception) {
        try {
            this.commitAllowingStateLoss()
        } catch (e1: Exception) {
            e1.printStackTrace()
        }
    }
}