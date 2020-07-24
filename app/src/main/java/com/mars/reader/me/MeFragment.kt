package com.mars.reader.me

import android.os.Bundle
import android.view.View
import com.mars.reader.R
import com.mars.reader.account.AccountManager
import com.mars.reader.core.base.ui.BaseActivity
import com.mars.reader.core.base.ui.BaseFragment
import com.mars.reader.ext.loadWithGlide
import com.mars.reader.home.HomeActivity
import com.mars.reader.rank.RankActivity
import com.mars.user.manager.UserManager
import kotlinx.android.synthetic.main.main_fragment_me.*
import org.jetbrains.anko.imageResource
import org.jetbrains.anko.sdk15.listeners.onClick

class MeFragment : BaseFragment() {

    private val currentUser by lazy {
        UserManager.currentUser
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as HomeActivity).actionBarController.setupWithViewPager(null)
        // 禁止下拉刷新
        refreshLayout.isEnabled = false
        initView()

        register(UserManager.observerLoginStatus().subscribe {
            updateView(it.isLogin())
        })
    }

    private fun initView() {
        updateView(AccountManager.isLogin())
        containerScore.onClick {
            RankActivity.startActivity(activity as BaseActivity)
        }
    }

    private fun updateView(isLogin: Boolean) {
        if (isLogin) {
            userAvatar.loadWithGlide(
                currentUser!!.icon,
                currentUser!!.nickname.first()
            )
            tvUserId.text = currentUser!!.id.toString()
        } else {
            userAvatar.imageResource = R.drawable.ic_github
            tvUserId.text = ""
        }
    }

    override fun getLayoutId(): Int = R.layout.main_fragment_me
}