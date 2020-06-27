package com.mars.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.mars.common_base.di.ViewModelFactory
import com.mars.common_base.ext.hideSoftInput
import com.mars.common_base.ext.yes
import com.mars.common_base.ui.BaseFragment
import com.mars.login.vm.LoginViewModel
import com.mars.util.widget.LoadingUtil
import kotlinx.android.synthetic.main.login_fragment.*
import org.jetbrains.anko.sdk15.listeners.onClick
import org.jetbrains.anko.support.v4.toast
import javax.inject.Inject

class LoginFragment : BaseFragment() {

    private lateinit var mLoginViewModel: LoginViewModel

    @Inject
    lateinit var mFactory: ViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.login_fragment, container, false)
    }

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