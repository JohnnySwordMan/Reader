package com.mars.util.widget

import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.animation.AnimationUtils
import com.mars.util.R
import kotlinx.android.synthetic.main.common_dialog_loading.*

/**
 * 自定义LoadingDialog
 */
class LoadingDialog private constructor(context: Context, theme: Int = 0) :
    ProgressDialog(context, theme) {

    private var isShowProgress = false
    private var mMessage: CharSequence? = null

    companion object {
        private const val MAX_PROGRESS = 100

        fun newInstance(context: Context, msg: String): LoadingDialog {
            val dialog = LoadingDialog(context, THEME_HOLO_LIGHT)
            dialog.setCanceledOnTouchOutside(false)
            dialog.setMessage(msg)
            return dialog
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.common_dialog_loading)
        isIndeterminate = false
        max = MAX_PROGRESS

        tvProgress.visibility = if (isShowProgress) View.VISIBLE else View.INVISIBLE
        tvMsg.text = mMessage
    }

    override fun onStart() {
        super.onStart()
        window?.let {
            val params = it.attributes
            params.dimAmount = 0.0f
            it.attributes = params
            it.setBackgroundDrawableResource(R.color.common_transparent)
        }
        rotate()
    }

    override fun setMessage(message: CharSequence?) {
        super.setMessage(message)
        this.mMessage = message
        tvMsg?.text = message
    }

    override fun setProgress(value: Int) {
        super.setProgress(value)
        isShowProgress = true
        tvProgress?.let {
            it.visibility = View.VISIBLE
            it.text = "$value%"
        }
    }

    private fun rotate() {
        ivLoading.startAnimation(AnimationUtils.loadAnimation(context, R.anim.common_anim_loading))
    }

    private fun clearAnimation() {
        ivLoading.clearAnimation()
    }

    override fun dismiss() {
        clearAnimation()
        super.dismiss()
    }

}