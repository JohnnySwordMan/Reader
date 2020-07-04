package com.mars.reader.knowledge

import android.content.Context
import android.os.Bundle
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
import com.mars.reader.knowledge.adapter.ChapterListAdapter
import com.mars.reader.knowledge.model.ChapterData
import com.mars.reader.knowledge.vm.KnowledgeViewModel
import com.github.jdsjlzx.recyclerview.LuRecyclerViewAdapter
import kotlinx.android.synthetic.main.frargment_system_tree.*
import javax.inject.Inject

class SystemTreeFragment : BaseFragment() {

    private lateinit var knowledgeViewModel: KnowledgeViewModel

    private lateinit var chapterListAdapter: ChapterListAdapter

    private lateinit var mActivity: BaseActivity


    @Inject
    lateinit var mFactory: ViewModelFactory


    override fun onAttach(context: Context) {
        super.onAttach(context)
        mActivity = context as BaseActivity
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        log("SystemTreeFragment")
        knowledgeViewModel =
            ViewModelProviders.of(this, mFactory).get(KnowledgeViewModel::class.java)

        chapterListAdapter = ChapterListAdapter(::clickDetailArticles)
        systemRecycler.adapter = LuRecyclerViewAdapter(chapterListAdapter)
        systemRecycler.setLoadMoreEnabled(false)
        systemRecycler.layoutManager =
            LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)
        systemRecycler.itemAnimator = DefaultItemAnimator()
        initData()

        observerData()
    }

    private fun observerData() {
        knowledgeViewModel.getChapterDataResult().observe(this, Observer {
            log("SystemTree---success---it = $it")
            it?.let {
                chapterListAdapter.data.clear()
                chapterListAdapter.data.addAll(it)
                systemRecycler.refreshComplete(it.size)
            }
        })

        knowledgeViewModel.getErrorResult().observe(this, Observer {
            log("SystemTree---error---it = $it")
        })
    }


    private fun initData() {
        knowledgeViewModel.getChapterList()
    }

    private fun clickDetailArticles(item: ChapterData) {
        KnowledgeActivity.startKnowledgeActivity(mActivity, item)
    }

    override fun getLayoutId(): Int {
        return R.layout.frargment_system_tree
    }


}