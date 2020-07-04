package com.mars.reader.project.module

import androidx.lifecycle.ViewModel
import com.mars.core.di.ViewModelKey
import com.mars.core.services.retrofit
import com.mars.reader.project.api.ProjectApi
import com.mars.reader.project.repo.ProjectRepository
import com.mars.reader.project.vm.ProjectViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
class ProjectModule {

    @Provides
    fun provideProjectApi(): ProjectApi {
        return retrofit.create(ProjectApi::class.java)
    }

    @Provides
    fun provideProjectRepository(api: ProjectApi): ProjectRepository {
        return ProjectRepository(api)
    }

    @Provides
    @IntoMap
    @ViewModelKey(ProjectViewModel::class)
    fun provideProjectViewModel(repo: ProjectRepository): ViewModel {
        return ProjectViewModel(repo)
    }
}