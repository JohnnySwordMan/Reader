package com.mars.login.module

import com.mars.common_base.di.PerFragment
import com.mars.login.LoginFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class LoginFragmentModule {

    @PerFragment
    @ContributesAndroidInjector
    abstract fun contributeLoginFragment(): LoginFragment
}