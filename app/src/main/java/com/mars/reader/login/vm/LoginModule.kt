package com.mars.reader.login.vm

import androidx.lifecycle.ViewModel
import com.mars.core.di.ViewModelKey
import com.mars.core.services.retrofit
import com.mars.reader.login.api.LoginApi
import com.mars.reader.login.repo.LoginRepository
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
    fun provideLoginRepository(loginApi: LoginApi): LoginRepository {
        return LoginRepository(loginApi)
    }

    @Provides
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    fun provideLoginViewModel(repo: LoginRepository): ViewModel {
        return LoginViewModel(repo)
    }

}