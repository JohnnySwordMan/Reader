package com.mars.reader.wechat.module

import androidx.lifecycle.ViewModel
import com.mars.core.di.ViewModelKey
import com.mars.core.services.retrofit
import com.mars.reader.wechat.api.WechatApi
import com.mars.reader.wechat.repo.WechatRepository
import com.mars.reader.wechat.vm.WechatViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
class WechatModule {

    @Provides
    fun provideWechatApi(): WechatApi {
        return retrofit.create(WechatApi::class.java)
    }

    @Provides
    fun provideWechatRepository(api: WechatApi): WechatRepository {
        return WechatRepository(api)
    }

    @Provides
    @IntoMap
    @ViewModelKey(WechatViewModel::class)
    fun prorvideWechatViewModel(repo: WechatRepository): ViewModel {
        return WechatViewModel(repo)
    }
}