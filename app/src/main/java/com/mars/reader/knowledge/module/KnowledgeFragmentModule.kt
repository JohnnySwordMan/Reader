package com.mars.reader.knowledge.module

import com.mars.reader.knowledge.KnowledgeNavigationFragment
import com.mars.reader.knowledge.SystemTreeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class KnowledgeFragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeSystemTreeFragment(): SystemTreeFragment


    @ContributesAndroidInjector
    abstract fun contributeKnowledgeNavigationFragment(): KnowledgeNavigationFragment
}