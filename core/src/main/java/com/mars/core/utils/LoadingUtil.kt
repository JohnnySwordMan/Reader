package com.mars.core.utils

import android.app.Activity
import com.mars.core.widgets.LoadingDialog


object LoadingUtil {

    private val dialogMap = HashMap<String, LoadingDialog>()

    fun show(activity: Activity, msg: String = "") {
        getProgressDialog(activity, msg, LoadingDialog.newInstance(activity, msg))
    }


    private fun getProgressDialog(activity: Activity?, msg: String, initDialog: LoadingDialog) {
        if (activity == null || activity.isFinishing) return
        val key = activity.toString()
        var dialog = dialogMap[key]
        if (dialog == null) {
            dialog = initDialog
            dialogMap[key] = dialog
        } else {
            dialog.setMessage(msg)
        }
        dialog.show()
    }

    fun dismiss(activity: Activity) {
        if (activity == null || activity.isFinishing) return
        val key = activity.toString()
        val dialog = dialogMap[key]
        if (dialog != null && dialog.isShowing) {
            dialog.dismiss()
        }
    }
}