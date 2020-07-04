package com.mars.reader.knowledge.module

import com.mars.core.di.PerFragment
import com.mars.reader.knowledge.KnowledgeArticlesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class KnowledgeArticlesFragmentModule {

    @PerFragment
    @ContributesAndroidInjector(modules = [KnowledgeModule::class])
    abstract fun contributeKnowledgeArticlesFragment(): KnowledgeArticlesFragment
}