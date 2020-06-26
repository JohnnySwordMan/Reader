package com.mars.login

import android.content.Context
import com.mars.loginapi.ILoginService

class LoginServiceImpl : ILoginService {

    override fun login(context: Context) {
        LoginActivity.startActivity(context)
    }

}