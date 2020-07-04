package com.mars.reader.home.vm

import com.mars.core.di.PerFragment
import com.mars.reader.knowledge.KnowledgeFragment
import com.mars.reader.knowledge.module.KnowledgeFragmentModule
import com.mars.reader.knowledge.module.KnowledgeModule
import com.mars.reader.home.HomeFragment
import com.mars.reader.me.MeFragment
import com.mars.reader.project.ProjectFragment
import com.mars.reader.project.module.ProjectFragmentModule
import com.mars.reader.project.module.ProjectModule
import com.mars.reader.wechat.WechatFragment
import com.mars.reader.wechat.module.WechatFragmentModule
import com.mars.reader.wechat.module.WechatModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentModule {

    @PerFragment
    @ContributesAndroidInjector(modules = [MainModule::class])
    abstract fun contributeMainFragment(): HomeFragment

    @PerFragment
    @ContributesAndroidInjector(modules = [KnowledgeFragmentModule::class, KnowledgeModule::class])
    abstract fun contributeKnowledgeFragment(): KnowledgeFragment


    @PerFragment
    @ContributesAndroidInjector(modules = [WechatFragmentModule::class, WechatModule::class])
    abstract fun contributeWechatFragment(): WechatFragment


    @PerFragment
    @ContributesAndroidInjector(modules = [ProjectFragmentModule::class, ProjectModule::class])
    abstract fun contributeProjectFragment(): ProjectFragment


    @PerFragment
    @ContributesAndroidInjector
    abstract fun contributeMeFragment(): MeFragment
}