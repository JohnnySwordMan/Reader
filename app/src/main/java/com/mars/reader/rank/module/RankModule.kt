package com.mars.reader.rank.module

import androidx.lifecycle.ViewModel
import com.mars.core.di.ViewModelKey
import com.mars.core.services.retrofit
import com.mars.reader.rank.api.RankApi
import com.mars.reader.rank.repo.RankRepository
import com.mars.reader.rank.vm.RankViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
class RankModule {

    @Provides
    fun provideRankApi(): RankApi {
        return retrofit.create(RankApi::class.java)
    }

    @Provides
    fun provideRankRepository(api: RankApi): RankRepository {
        return RankRepository(api)
    }

    @Provides
    @IntoMap
    @ViewModelKey(RankViewModel::class)
    fun provideRankViewModel(repository: RankRepository): ViewModel {
        return RankViewModel(repository)
    }
}