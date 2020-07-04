package com.mars.reader.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import com.mars.core.ext.log
import com.mars.reader.R
import com.mars.reader.account.AccountManager
import com.mars.reader.core.base.ui.BaseActivity
import com.mars.reader.ext.afterClosed
import com.mars.core.ext.otherwise
import com.mars.reader.ext.showFragment
import com.mars.core.ext.yes
import com.mars.user.manager.UserManager
import com.mars.user.model.UserEvent
import com.mars.reader.login.LoginActivity
import com.mars.reader.home.nav.NavigationController
import com.mars.reader.home.nav.NavigationItem
import com.mars.reader.rank.RankActivity
import com.mars.reader.view.ActionBarController
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_simple.*
import kotlinx.android.synthetic.main.main_app_bar.*
import org.jetbrains.anko.toast

class HomeActivity : BaseActivity() {

    private val navigationController by lazy {
        NavigationController(
            navigationView,
            ::onNavigationItemChanged,
            ::onHeaderClick,
            ::onRankClick
        )
    }

    val actionBarController by lazy {
        ActionBarController(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        initActionBar()
        initNavigation()

        register(UserManager.observerLoginStatus().subscribe {
            log("update UserInfo")
            updateView(it)
        })
    }

    private fun updateView(userEvent: UserEvent) {
        if (userEvent.isLogin()) {
            navigationController.updateView()
        } else if (userEvent.isLogout()) {
            navigationController.updateNoLoginView()
        }
    }

    private fun initNavigation() {
        navigationController.apply {
            updateView()
            selectProperItem()
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


    private fun onNavigationItemChanged(item: NavigationItem) {
        drawerLayout.afterClosed {
            showFragment(R.id.fragmentContainer, item.fragmentClass, item.arguments)
            toolBar.title = item.title
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
            LoginActivity.startActivity(this)
        }
    }

    private fun onRankClick() {
        log("click rank")
        RankActivity.startRankActivity(this)
    }

    override fun canImmersiveStyle(): Boolean {
        return true
    }


    companion object {
        fun startActivity(context: Context) {
            val intent = Intent(context, HomeActivity::class.java)
            context.startActivity(intent)
        }
    }
}
