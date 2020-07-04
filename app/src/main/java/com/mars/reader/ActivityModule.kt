package com.mars.reader

import com.mars.core.di.PerActivity
import com.mars.reader.knowledge.KnowledgeActivity
import com.mars.reader.knowledge.module.KnowledgeArticlesFragmentModule
import com.mars.reader.login.LoginActivity
import com.mars.reader.login.vm.LoginModule
import com.mars.reader.home.HomeActivity
import com.mars.reader.home.vm.MainFragmentModule
import com.mars.reader.login.vm.LoginFragmentModule
import com.mars.reader.rank.RankActivity
import com.mars.reader.rank.module.RankModule
import com.mars.reader.web.WebActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {


    @PerActivity
    @ContributesAndroidInjector(modules = [MainFragmentModule::class])
    abstract fun contributeMainActivity(): HomeActivity

    /**
     * LoginFragmentModule一定得写，
     * 否则会报错：java.lang.IllegalArgumentException: No injector factory bound for Class
     */
    @PerActivity
    @ContributesAndroidInjector(modules = [LoginModule::class, LoginFragmentModule::class])
    abstract fun contributeLoginActivity(): LoginActivity


    @PerActivity
    @ContributesAndroidInjector(modules = [RankModule::class])
    abstract fun contributeRankActivity(): RankActivity

    @PerActivity
    @ContributesAndroidInjector
    abstract fun contributeWebActivity(): WebActivity

    @PerActivity
    @ContributesAndroidInjector(modules = [KnowledgeArticlesFragmentModule::class])
    abstract fun contributeKnowledgeActivity(): KnowledgeActivity
}