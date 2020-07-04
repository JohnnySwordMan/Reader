package com.mars.reader.wechat.module

import com.mars.reader.wechat.WechatArticleFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class WechatFragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeWechatArticleFragment(): WechatArticleFragment

}