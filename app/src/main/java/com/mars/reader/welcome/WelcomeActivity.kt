package com.mars.reader.welcome

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mars.reader.R
import com.mars.reader.home.HomeActivity
import kotlinx.android.synthetic.main.activity_welcome.*
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class WelcomeActivity : AppCompatActivity() {

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

        mainScope.launch {
            delay(TIME_WELCOME)
            goNext()
        }
    }

    private fun goNext() {
        HomeActivity.startActivity(this)
        finish()
    }


    companion object {
        private const val TAG = "Welcome"
        private const val TIME_WELCOME = 2000L
    }
}