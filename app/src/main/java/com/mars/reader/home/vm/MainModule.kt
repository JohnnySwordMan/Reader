package com.mars.reader.home.vm

import androidx.lifecycle.ViewModel
import com.mars.core.di.ViewModelKey
import com.mars.core.services.retrofit
import com.mars.reader.home.api.MainApi
import com.mars.reader.home.repo.MainRepository
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
class MainModule {

    @Provides
    fun provideMainApi(): MainApi {
        return retrofit.create(MainApi::class.java)
    }

    @Provides
    fun provideMainRepository(api: MainApi): MainRepository {
        return MainRepository(api)
    }

    @Provides
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun provideMainViewModel(repo: MainRepository): ViewModel {
        return MainViewModel(repo)
    }
}