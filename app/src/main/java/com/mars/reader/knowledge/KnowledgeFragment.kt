package com.mars.reader.knowledge

import com.mars.core.ext.log
import com.mars.reader.core.base.model.FragmentPage
import com.mars.reader.core.base.ui.CommonPagerFragment

class KnowledgeFragment : CommonPagerFragment() {

    override fun getFragmentPages(): List<FragmentPage> {
        log("KnowledgeFragment---getFragmentPages")
        return listOf(
            FragmentPage(SystemTreeFragment(), "体系"),
            FragmentPage(KnowledgeNavigationFragment(), "导航")
        )
    }
}