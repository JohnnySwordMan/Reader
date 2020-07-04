package com.mars.reader.wechat

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.mars.core.di.ViewModelFactory
import com.mars.core.ext.log
import com.mars.reader.R
import com.mars.reader.core.base.adapter.CommonStatePagerAdapter
import com.mars.reader.core.base.model.FragmentPage
import com.mars.reader.core.base.ui.BaseFragment
import com.mars.reader.knowledge.model.ChapterData
import com.mars.reader.home.HomeActivity
import com.mars.reader.wechat.vm.WechatViewModel
import kotlinx.android.synthetic.main.main_fragment_wechat.*
import javax.inject.Inject

class WechatFragment : BaseFragment() {


    private lateinit var wechatViewModel: WechatViewModel

    @Inject
    lateinit var mFactory: ViewModelFactory


    private val viewPageAdapter by lazy {
        CommonStatePagerAdapter(childFragmentManager)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        wechatViewModel = ViewModelProviders.of(this, mFactory).get(WechatViewModel::class.java)
        wechatViewPager.adapter = viewPageAdapter
        (activity as HomeActivity).actionBarController.setupWithViewPager(wechatViewPager)
        initData()
        observerData()
    }

    private fun observerData() {
        wechatViewModel.getWechatArticlesResult().observe(this, Observer {
            it?.let {
                createFragments(it)
            }
        })
        wechatViewModel.getErrorResult().observe(this, Observer {
            it?.let {

            }
        })
    }

    private fun createFragments(list: List<ChapterData>) {
        val arr = ArrayList<FragmentPage>()
        for (chapterData in list) {
            log("WechatFragment---createFragments---name = ${chapterData.name}")
            arr.add(FragmentPage(WechatArticleFragment.newInstance(chapterData.id), chapterData.name))
        }
        viewPageAdapter.fragmentPageData.addAll(arr)
    }

    private fun initData() {
        wechatViewModel.getWechatArticles()
    }

    override fun getLayoutId(): Int = R.layout.main_fragment_wechat

}