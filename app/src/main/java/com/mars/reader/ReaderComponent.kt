package com.mars.reader

import android.app.Application
import com.mars.common_base.di.PerApplication
import com.mars.reader.module.ActivityModule
import com.mars.reader.module.AppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@PerApplication
@Component(modules = [AndroidSupportInjectionModule::class, AppModule::class, ActivityModule::class])
interface ReaderComponent : AndroidInjector<ReaderApplication> {

    @Component.Builder
    interface Builder {

        fun build(): ReaderComponent

        @BindsInstance
        fun application(application: Application): Builder
    }
}