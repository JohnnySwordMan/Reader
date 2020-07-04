package com.mars.reader.me

import android.os.Bundle
import android.view.View
import com.mars.reader.R
import com.mars.reader.account.AccountManager
import com.mars.reader.core.base.ui.BaseFragment
import com.mars.reader.ext.loadWithGlide
import com.mars.reader.home.HomeActivity
import com.mars.user.manager.UserManager
import kotlinx.android.synthetic.main.main_fragment_me.*
import org.jetbrains.anko.imageResource

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
    }

    private fun initView() {
        if (AccountManager.isLogin()) {
            userAvatar.loadWithGlide(
                currentUser!!.icon,
                currentUser!!.nickname.first()
            )
            tvUserId.text = currentUser!!.id.toString()
        } else {
            userAvatar.imageResource = R.drawable.ic_github
        }

    }

    override fun getLayoutId(): Int = R.layout.main_fragment_me
}