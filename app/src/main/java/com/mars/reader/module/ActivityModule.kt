package com.mars.reader.module

import com.mars.common_base.di.PerActivity
import com.mars.login.LoginActivity
import com.mars.login.module.LoginFragmentModule
import com.mars.login.module.LoginModule
import com.mars.reader.MainActivity
import com.mars.reader.WelcomeActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {


    @PerActivity
    @ContributesAndroidInjector
    abstract fun contributeWelcomeActivity(): WelcomeActivity

    @PerActivity
    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity

    @PerActivity
    @ContributesAndroidInjector(modules = [LoginModule::class, LoginFragmentModule::class])
    abstract fun contributeLoginActivity(): LoginActivity
}