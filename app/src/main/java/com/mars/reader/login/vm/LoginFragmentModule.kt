package com.mars.reader.login.vm

import com.mars.core.di.PerFragment
import com.mars.reader.login.LoginFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class LoginFragmentModule {

    @PerFragment
    @ContributesAndroidInjector
    abstract fun contributeLoginFragment(): LoginFragment
}