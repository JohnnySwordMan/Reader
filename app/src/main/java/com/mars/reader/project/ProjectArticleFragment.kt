package com.mars.reader.project

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.mars.core.di.ViewModelFactory
import com.mars.reader.R
import com.mars.reader.core.base.ui.BaseFragment
import com.mars.reader.home.HomeActivity
import com.mars.reader.home.adapter.ArticleListAdapter
import com.mars.reader.home.model.ArticlePage
import com.mars.reader.project.vm.ProjectViewModel
import com.github.jdsjlzx.recyclerview.LuRecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_articles_page.*
import org.jetbrains.anko.support.v4.onRefresh
import javax.inject.Inject

class ProjectArticleFragment : BaseFragment() {

    private lateinit var projectViewModel: ProjectViewModel

    private lateinit var articleListAdapter: ArticleListAdapter

    private var cid = 0
    private var currentPage = 1

    @Inject
    lateinit var mFactory: ViewModelFactory

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        projectViewModel = ViewModelProviders.of(this, mFactory).get(ProjectViewModel::class.java)

        refreshView.setColorSchemeResources(
            R.color.google_red,
            R.color.google_yellow,
            R.color.google_green,
            R.color.google_blue
        )

        articleListAdapter = ArticleListAdapter(activity as HomeActivity)

        recyclerView.apply {
            adapter = LuRecyclerViewAdapter(articleListAdapter)
            setLoadMoreEnabled(true)
            layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)
            itemAnimator = DefaultItemAnimator()
            setOnLoadMoreListener {
                projectViewModel.loadMoreData(currentPage + 1, cid)
            }
        }

        refreshView.isRefreshing = true
        refreshView.onRefresh {
            projectViewModel.refreshData(cid)
        }

        initData()
        observerData()
    }

    private fun observerData() {
        projectViewModel.getProjectArticlesResult().observe(this, Observer {
            it?.let {
                currentPage = it.curPage
                if (it.curPage == 1) {
                    onDataInit(it)
                } else if (it.curPage > 1) {
                    onDataLoadMore(it)
                }
            } ?: run {

            }
        })
        projectViewModel.getErrorResult().observe(this, Observer {
            it?.let {

            }
        })
    }

    private fun onDataLoadMore(articlePage: ArticlePage) {
        articleListAdapter.data.update(articlePage.datas)
        recyclerView.setNoMore(articlePage.over)
        recyclerView.refreshComplete(articlePage.size)
    }

    private fun onDataInit(articlePage: ArticlePage) {
        articleListAdapter.data.clear()
        articleListAdapter.data.addAll(articlePage.datas)
        recyclerView.setNoMore(articlePage.over)
        recyclerView.refreshComplete(articlePage.size)
        refreshView.isRefreshing = false
    }

    private fun initData() {
        cid = arguments?.getInt(KEY_CID, 0)!!
        projectViewModel.getProjectArticlesById(currentPage, cid)
    }

    override fun getLayoutId(): Int = R.layout.fragment_articles_page


    companion object {

        const val KEY_CID = "cid"

        fun newInstance(cid: Int) = ProjectArticleFragment().apply {
            arguments = Bundle().apply {
                putInt(KEY_CID, cid)
            }
        }
    }
}