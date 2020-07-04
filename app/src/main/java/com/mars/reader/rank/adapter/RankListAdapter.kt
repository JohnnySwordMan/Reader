package com.mars.reader.rank.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.mars.core.ext.log
import com.mars.reader.R
import com.mars.reader.core.base.adapter.CommonListAdapter
import com.mars.reader.rank.model.RankInfo
import kotlinx.android.synthetic.main.layout_item_rank.view.*
import org.jetbrains.anko.backgroundResource

class RankListAdapter : CommonListAdapter<RankInfo>(R.layout.layout_item_rank) {

    override fun onItemClick(itemView: View, item: RankInfo) {
    }

    override fun bindData(viewHolder: RecyclerView.ViewHolder, item: RankInfo) {
        viewHolder.itemView.apply {
            // holder重用，需要设置INVISIBLE
            containerRankIcon.visibility = View.INVISIBLE
            when {
                item.rank == 1 -> {
                    containerRankIcon.backgroundResource = R.drawable.ic_rank_first
                    containerRankIcon.visibility = View.VISIBLE
                }
                item.rank == 2 -> {
                    containerRankIcon.backgroundResource = R.drawable.ic_rank_second
                    containerRankIcon.visibility = View.VISIBLE
                }
                item.rank == 3 -> {
                    containerRankIcon.backgroundResource = R.drawable.ic_rank_third
                    containerRankIcon.visibility = View.VISIBLE
                }
            }
            tvRankNum.text = item.rank.toString()
            tvRankUserName.text = item.username
            tvRankScore.text = item.coinCount.toString()
            log("rank = ${item.rank}")
        }
    }

}