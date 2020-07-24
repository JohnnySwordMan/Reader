package com.mars.base.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.mars.base.R

/**
 * 自定义标题栏
 */
class RTitleBarView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RelativeLayout(context, attrs, defStyleAttr) {

    private var ivLeft: ImageView? = null
    private var tvTitle: TextView? = null
    private var tvRight: TextView? = null

    init {
        val inflate = LayoutInflater.from(context).inflate(R.layout.r_title_bar_view, this)
        ivLeft = inflate.findViewById<ImageView>(R.id.iv_left)
        tvTitle = inflate.findViewById<TextView>(R.id.tv_title)
        tvRight = inflate.findViewById<TextView>(R.id.tv_right)
    }

    fun setTitle(title: String) {
        tvTitle?.text = title
    }

    fun setRightText(text: String) {
        tvRight?.text = text
    }

    fun setLeftText(text: String) {
        tvTitle?.text = text
    }
}