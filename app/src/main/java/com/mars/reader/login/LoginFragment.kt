package com.mars.reader.login

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.mars.base.util.Logger
import com.mars.core.ab.CoroutinesManager
import com.mars.core.annotation.Tips
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

/**
 * @Tips
 * Ui层所有的变化，都应该由ViewModel决定。例如：这里点击登录按钮，Ui层主动触发了loading，这个其实也不合理
 */
class LoginFragment : BaseFragment() {

    private lateinit var mLoginViewModel: LoginViewModel

    @Inject
    lateinit var mFactory: ViewModelFactory

    override fun getLayoutId(): Int = R.layout.fragment_login

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mLoginViewModel = ViewModelProviders.of(this, mFactory)[LoginViewModel::class.java]

        initView()
        startObserver()
    }

    private fun startObserver() {
        mLoginViewModel.getLoginResult().observe(this, Observer {
            dismissLoading()
            if (it != null) {
                toast(getString(R.string.login_success))
                activity?.finish()
            }
        })

        mLoginViewModel.getErrorResult().observe(this, Observer {
            dismissLoading()
            if (it != null) {
                toast(it.errorMsg)
            }
        })

        mLoginViewModel.getUiState().observe(this, Observer {
            if (it == null) {
                return@Observer
            }
            Logger.e("gy", "LoginFragment---LoginUiState---enableLogin = ${it.enableBtn}")
            if (it.isLoading) {
                showLoading()
            }
            it.isSuccess?.let {
                dismissLoading()
                toast("协程：${getString(R.string.login_success)}")
                activity?.finish()
            }
            it.isError?.let {
                dismissLoading()
                toast("协程：$it")
            }
        })
    }


    private fun initView() {
        btnSubmit.onClick {
            val username = username.text.toString().trim()
            val password = password.text.toString().trim()

            if (!CoroutinesManager.useCoroutines) {
                @Tips("主动触发loading，也不合理")
                LoadingUtil.show(activity!!, getString(R.string.login_ing))
                clickLogin(username, password)
            } else {
                mLoginViewModel.login(username, password)
            }
        }
    }

    private fun clickLogin(username: String?, password: String?) {
        @Tips("这里代码也是不合理的，ui层应该只调用login方法，至于这些判断应该放在ViewModel层，对ui层是透明的")
        mLoginViewModel.checkUsernameAndPassword(
            username,
            password
        ).yes {
            activity?.hideSoftInput()
            mLoginViewModel.login(username, password)
        }
    }

    private fun dismissLoading() {
        LoadingUtil.dismiss(activity!!)
    }

    private fun showLoading() {
        LoadingUtil.show(activity!!, getString(R.string.login_ing))
    }


    companion object {

        const val TAG = "login"

        fun newInstance(): LoginFragment = LoginFragment().apply {

        }
    }
}