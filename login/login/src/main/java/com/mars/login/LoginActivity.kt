package com.mars.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.FragmentTransaction
import com.mars.common_base.ui.BaseActivity
import com.mars.util.ext.runSafely
import com.mars.util.ext.runSafelyV2

class LoginActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)

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