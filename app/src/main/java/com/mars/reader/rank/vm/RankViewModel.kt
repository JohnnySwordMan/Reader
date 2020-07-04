package com.mars.reader.rank.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mars.core.exception.ApiErrorCode
import com.mars.core.exception.ApiException
import com.mars.core.rx.RxViewModel
import com.mars.reader.rank.model.RankPage
import com.mars.reader.rank.repo.RankRepository

class RankViewModel(private val repository: RankRepository) : RxViewModel() {

    private var rankPageData = MutableLiveData<RankPage>()

    private var errorResult = MutableLiveData<Pair<Int, String>>()

    fun getRankPageData(): LiveData<RankPage> {
        return rankPageData
    }

    fun getErrorResult(): LiveData<Pair<Int, String>> {
        return errorResult
    }

    fun getRankList(page: Int) {
        register(repository.getRankList(page).subscribe({
            if (it != null) {
                rankPageData.value = it
            } else {
                errorResult.value = Pair(ApiErrorCode.ERROR_DATA_EMPTY, "没有数据！")
            }
        }, {
            val exception = it as ApiException
            errorResult.value = Pair(exception.code, exception.msg ?: "请求失败")
        }))
    }

    fun loadMoreData(nextPage: Int) {
        getRankList(nextPage)
    }
}