package com.mars.reader.rank

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.mars.core.di.ViewModelFactory
import com.mars.reader.R
import com.mars.reader.core.base.ui.BaseActivity
import com.mars.reader.rank.adapter.RankListAdapter
import com.mars.reader.rank.model.RankPage
import com.mars.reader.rank.vm.RankViewModel
import com.github.jdsjlzx.recyclerview.LuRecyclerViewAdapter
import com.mars.base.view.RTitleBarView
import kotlinx.android.synthetic.main.activity_rank.*
import org.jetbrains.anko.sdk15.listeners.onClick
import javax.inject.Inject

class RankActivity : BaseActivity() {

    private lateinit var rankViewModel: RankViewModel
    private lateinit var rankListAdapter: RankListAdapter
    private var titleView: RTitleBarView? = null
    private var ivClose: ImageView? = null

    private var currentPage = PAGE_START

    @Inject
    lateinit var mFactory: ViewModelFactory


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rank)
        initView()

        rankViewModel = ViewModelProviders.of(this, mFactory).get(RankViewModel::class.java)

        rankListAdapter = RankListAdapter()
        recyclerView.adapter = LuRecyclerViewAdapter(rankListAdapter)
        recyclerView.setLoadMoreEnabled(true)
        recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.setOnLoadMoreListener {
            rankViewModel.loadMoreData(currentPage + 1)
        }
        initData()
        observerData()
    }

    private fun initView() {
        titleView = findViewById(R.id.layout_title_bar)
        ivClose = findViewById(R.id.iv_left)
        titleView?.setTitle(getString(R.string.rank_title))
        ivClose!!.onClick {
            finish()
        }
    }

    private fun observerData() {
        rankViewModel.apply {
            getRankPageData().observe(this@RankActivity, Observer {
                it?.let {
                    currentPage = it.curPage
                    if (it.curPage == 1) {
                        onDataInit(it)
                    } else {
                        onDataLoadMore(it)
                    }
                }
            })
            getErrorResult().observe(this@RankActivity, Observer {
                it?.let {

                }
            })
        }
    }

    private fun onDataLoadMore(rankPage: RankPage) {
        rankListAdapter.data.update(rankPage.datas)
        recyclerView.setNoMore(rankPage.over)
        recyclerView.refreshComplete(rankPage.size)
    }

    private fun onDataInit(rankPage: RankPage) {
        rankListAdapter.data.clear()
        rankListAdapter.data.addAll(rankPage.datas)
        recyclerView.setNoMore(rankPage.over)
        recyclerView.refreshComplete(rankPage.size)
    }

    private fun initData() {
        rankViewModel.getRankList(currentPage)
    }


    companion object {

        const val PAGE_START = 1

        fun startActivity(activity: BaseActivity) {
            val intent = Intent(activity, RankActivity::class.java)
            activity.startActivity(intent)
        }
    }
}