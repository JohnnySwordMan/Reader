package com.mars.login.module

import androidx.lifecycle.ViewModel
import com.mars.common_base.di.ViewModelKey
import com.mars.common_base.service.retrofit
import com.mars.login.api.LoginApi
import com.mars.login.repo.ILoginRepository
import com.mars.login.repo.LoginRepository
import com.mars.login.vm.LoginViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
class LoginModule {


    @Provides
    fun provideAuthApi(): LoginApi {
        return retrofit.create(LoginApi::class.java)
    }

    @Provides
    fun provideLoginRepository(loginApi: LoginApi): ILoginRepository {
        return LoginRepository(loginApi)
    }

    @Provides
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    fun provideLoginViewModel(repo: ILoginRepository): ViewModel {
        return LoginViewModel(repo)
    }
}