package com.mars.reader

import android.os.Bundle
import com.mars.common_base.ui.BaseActivity
import com.mars.util.ext.Logger
import kotlinx.android.synthetic.main.activity_welcome.*
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class WelcomeActivity : BaseActivity() {

    private val mainScope = MainScope()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        lottieWelcome.speed = 5.0f

        // 倒计时
//        Observable.interval(2, TimeUnit.SECONDS)
//            .take(1)
//            .observeOn(AndroidSchedulers.mainThread())
//            .doOnNext {
//                goNext()
//            }.subscribe()

        Logger.e(TAG, "before start coroutine launch")
        mainScope.launch {
            delay(TIME_WELCOME)
            goNext()
        }
    }

    private fun goNext() {
        Logger.e(TAG, "goNext")
        MainActivity.startActivity(this)
        finish()
    }


    companion object {
        private const val TAG = "Welcome"
        private const val TIME_WELCOME = 2000L
    }
}