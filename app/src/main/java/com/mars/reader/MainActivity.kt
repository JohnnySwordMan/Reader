package com.mars.reader

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import com.mars.common_base.ext.otherwise
import com.mars.common_base.ext.showFragment
import com.mars.common_base.ext.yes
import com.mars.common_base.ui.BaseActivity
import com.mars.common_base.user.manager.UserManager
import com.mars.common_base.user.model.UserEvent
import com.mars.login.LoginActivity
import com.mars.loginapi.ILoginService
import com.mars.reader.account.AccountManager
import com.mars.reader.ext.afterClosed
import com.mars.reader.navigation.NavigationController
import com.mars.reader.navigation.NavigationItem
import com.mars.util.ext.Logger
import com.mars.util.widget.LoadingDialog
import com.mars.util.widget.LoadingUtil
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.main_app_bar.*
import org.jetbrains.anko.sdk15.listeners.onClick
import org.jetbrains.anko.toast
import java.util.*

class MainActivity : BaseActivity() {

    private val navigationController by lazy {
        NavigationController(
            navigationView,
            ::onNavigationItemChanged,
            ::onHeaderClick,
            ::onRankClick
        )
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolBar)
        initActionBar()
        initNavigation()

        register(UserManager.observerLoginStatus().subscribe {
            Logger.e("gy", "update UserStatus")
            updateView(it)
        })

//        btnStart.onClick {
////            LoadingUtil.show(this, "测试中...")
////            LoginActivity.startActivity(this)
//            login()
//        }

//        btnEnd.onClick {
//            LoadingUtil.dismiss(this)
//        }
    }

    private fun updateView(it: UserEvent) {
        if (it.isLogin()) {
            navigationController.updateView()
        } else if (it.isLogout()) {
            navigationController.updateNoLoginView()
        }
    }

    private fun onNavigationItemChanged(item: NavigationItem) {
        drawerLayout.afterClosed {
            showFragment(R.id.fragmentContainer, item.fragmentClass, item.arguments)
            toolBar.title = item.title
        }
    }

    private fun initActionBar() {
        val toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolBar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.setDrawerListener(toggle)
        toggle.syncState()  // toolbar和drawerLayout联动
    }

    private fun initNavigation() {
        navigationController.apply {
            updateView()
            selectProperItem()
        }
    }


    private fun onHeaderClick() {
        AccountManager.isLogin().yes {
            val dialog = AlertDialog.Builder(this)
                .setTitle("提示")
                .setMessage("确认注销吗？")
                .setPositiveButton("注销") { _, _ ->
                    AccountManager.logout().subscribe({
                        toast("注销成功")
                    }, {
                        it.printStackTrace()
                    })
                }
                .setNegativeButton("取消") { dialog, _ ->
                    dialog.dismiss()
                }
                .create()
            dialog.show()
            dialog.setOnDismissListener {
                dialog.dismiss()
            }
        }.otherwise {
            login()
        }
    }

    private fun onRankClick() {
        Logger.e("gy", "click rank")
    }

    // TODO 待优化
    private fun login() {
        val loader = ServiceLoader.load(ILoginService::class.java)
        val iterator = loader.iterator() as Iterator<ILoginService>
        if (iterator.hasNext()) {
            val service = iterator.next()
            service.login(this)
        }
    }


    companion object {

        fun startActivity(context: Context) {
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }
    }

}