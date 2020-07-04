package com.mars.reader.knowledge.model

import android.os.Parcelable
import com.mars.core.anno.PoKo
import com.mars.reader.home.model.Article
import kotlinx.android.parcel.Parcelize

@PoKo
@Parcelize
data class KnowledgeNavigationData(
    var name: String,
    var cid: Int,
    var articles: ArrayList<Article>?
) : Parcelable