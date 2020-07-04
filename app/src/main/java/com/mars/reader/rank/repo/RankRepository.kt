package com.mars.reader.rank.repo

import com.mars.core.utils.RxUtil
import com.mars.reader.rank.api.RankApi
import com.mars.reader.rank.model.RankPage
import io.reactivex.Observable

class RankRepository(private val api: RankApi) {

    fun getRankList(page: Int): Observable<RankPage> {
        return api.getRankList(page)
            .compose(RxUtil.applySchedulers())
            .compose(RxUtil.handleResult())
    }
}