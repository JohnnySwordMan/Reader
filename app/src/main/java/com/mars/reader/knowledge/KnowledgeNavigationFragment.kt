package com.mars.reader.knowledge

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.mars.core.di.ViewModelFactory
import com.mars.core.ext.log
import com.mars.reader.R
import com.mars.reader.core.base.ui.BaseActivity
import com.mars.reader.core.base.ui.BaseFragment
import com.mars.reader.knowledge.adapter.KnowledgeNavigationListAdapter
import com.mars.reader.knowledge.vm.KnowledgeViewModel
import com.mars.reader.web.WebActivity
import com.github.jdsjlzx.recyclerview.LuRecyclerViewAdapter
import kotlinx.android.synthetic.main.frargment_system_tree.*
import org.jetbrains.anko.toast
import javax.inject.Inject

class KnowledgeNavigationFragment : BaseFragment() {

    private lateinit var knowledgeViewModel: KnowledgeViewModel

    private lateinit var knowledgeNavListAdapter: KnowledgeNavigationListAdapter


    @Inject
    lateinit var mFactory: ViewModelFactory


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        log("KnowledgeNavigationFragment")


        knowledgeViewModel =
            ViewModelProviders.of(this, mFactory).get(KnowledgeViewModel::class.java)

        knowledgeNavListAdapter = KnowledgeNavigationListAdapter(::onItemClick)
        systemRecycler.adapter = LuRecyclerViewAdapter(knowledgeNavListAdapter)
        systemRecycler.setLoadMoreEnabled(false)
        systemRecycler.layoutManager =
            LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)
        systemRecycler.itemAnimator = DefaultItemAnimator()
        initData()
        observerData()
    }

    private fun observerData() {
        knowledgeViewModel.getKnowledgeNaviDataResult().observe(this, Observer {
            it?.let {
                knowledgeNavListAdapter.data.clear()
                knowledgeNavListAdapter.data.addAll(it)
                systemRecycler.refreshComplete(it.size)
            }
        })
        knowledgeViewModel.getErrorResult().observe(this, Observer {
            it?.let {

            }
        })
    }

    private fun initData() {
        knowledgeViewModel.getKnowledgeNaviList()
    }

    private fun onItemClick(linkUrl: String) {
        if (!TextUtils.isEmpty(linkUrl)) {
            WebActivity.startWebActivity(context as BaseActivity, linkUrl)
        } else {
            context?.toast("link为空")
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.frargment_system_tree
    }


}