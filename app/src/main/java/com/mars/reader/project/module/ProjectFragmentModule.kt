package com.mars.reader.project.module

import com.mars.reader.project.ProjectArticleFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ProjectFragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeProjectArticleFragment(): ProjectArticleFragment

}