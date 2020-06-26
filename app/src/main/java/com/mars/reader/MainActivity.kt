package com.mars.reader

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mars.common_base.ui.BaseActivity
import com.mars.util.widget.LoadingDialog
import com.mars.util.widget.LoadingUtil
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.sdk15.listeners.onClick

class MainActivity : BaseActivity() {


    companion object {

        fun startActivity(context: Context) {
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnStart.onClick {
            LoadingUtil.show(this, "测试中...")
        }

        btnEnd.onClick {
            LoadingUtil.dismiss(this)
        }
    }
}