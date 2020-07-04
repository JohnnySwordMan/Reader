package com.mars.reader.login

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.mars.core.di.ViewModelFactory
import com.mars.core.ext.hideSoftInput
import com.mars.core.ext.yes
import com.mars.core.utils.LoadingUtil
import com.mars.reader.R
import com.mars.reader.core.base.ui.BaseFragment
import com.mars.reader.login.vm.LoginViewModel
import kotlinx.android.synthetic.main.fragment_login.*
import org.jetbrains.anko.sdk15.listeners.onClick
import org.jetbrains.anko.support.v4.toast
import javax.inject.Inject

class LoginFragment : BaseFragment() {

    private lateinit var mLoginViewModel: LoginViewModel

    @Inject
    lateinit var mFactory: ViewModelFactory

    override fun getLayoutId(): Int = R.layout.fragment_login

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mLoginViewModel = ViewModelProviders.of(this, mFactory)[LoginViewModel::class.java]

        mLoginViewModel.getLoginResult().observe(this, Observer {
            LoadingUtil.dismiss(activity!!)
            if (it != null) {
                toast(getString(R.string.login_success))
                activity?.finish()
            }
        })

        mLoginViewModel.getErrorResult().observe(this, Observer {
            LoadingUtil.dismiss(activity!!)
            if (it != null) {
                toast(it.errorMsg)
            }
        })
        initView()
    }

    private fun initView() {
        btnSubmit.onClick {
            LoadingUtil.show(activity!!, getString(R.string.login_ing))
            val username = username.text.toString().trim()
            val password = password.text.toString().trim()

            mLoginViewModel.checkUsernameAndPassword(
                username,
                password
            ).yes {
                activity?.hideSoftInput()
                mLoginViewModel.login(username, password)
            }
        }
    }


    companion object {

        const val TAG = "login"

        fun newInstance(): LoginFragment = LoginFragment().apply {

        }
    }
}