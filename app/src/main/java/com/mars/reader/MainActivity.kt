package com.mars.reader

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mars.common_base.ui.BaseActivity
import com.mars.login.LoginActivity
import com.mars.loginapi.ILoginService
import com.mars.util.widget.LoadingDialog
import com.mars.util.widget.LoadingUtil
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.sdk15.listeners.onClick
import java.util.*

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
//            LoadingUtil.show(this, "测试中...")
//            LoginActivity.startActivity(this)
            login()
        }

        btnEnd.onClick {
            LoadingUtil.dismiss(this)
        }
    }

    // TODO 待优化
    private fun login() {
        val loader = ServiceLoader.load(ILoginService::class.java)
        val iterator = loader.iterator() as Iterator<ILoginService>
        if (iterator.hasNext()) {
            val service = iterator.next()
            service.login(this)
        }
    }
}