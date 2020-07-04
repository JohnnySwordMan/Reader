package com.mars.reader.core.base.ui

import android.os.Bundle
import android.view.View
import com.mars.reader.R
import com.mars.reader.core.base.adapter.CommonStatePagerAdapter
import com.mars.reader.core.base.model.FragmentPage
import com.mars.reader.home.HomeActivity
import kotlinx.android.synthetic.main.fragment_common.*

abstract class CommonStatePagerFragment : BaseFragment() {

    private val viewPageAdapter by lazy {
        CommonStatePagerAdapter(childFragmentManager)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        commonViewPager.adapter = viewPageAdapter
        

        (activity as HomeActivity).actionBarController.setupWithViewPager(commonViewPager)
        viewPageAdapter.fragmentPageData.addAll(getFragmentPages())
    }


    override fun getLayoutId(): Int {
        return R.layout.fragment_common
    }


    abstract fun getFragmentPages(): List<FragmentPage>

}