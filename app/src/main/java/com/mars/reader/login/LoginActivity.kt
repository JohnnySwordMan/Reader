package com.mars.reader.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.mars.core.ext.runSafely
import com.mars.reader.R
import com.mars.reader.core.base.ui.BaseActivity

class LoginActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val beginTransaction = supportFragmentManager.beginTransaction()
        runSafely(beginTransaction) {
            val fragment = LoginFragment.newInstance()
            beginTransaction.add(R.id.container_frameLayout, fragment)
            beginTransaction.commit()
        }
    }

    companion object {

        fun startActivity(context: Context) {
            val intent = Intent(context, LoginActivity::class.java)
            context.startActivity(intent)
        }
    }
}