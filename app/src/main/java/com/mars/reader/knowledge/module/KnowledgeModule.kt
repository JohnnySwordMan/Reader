package com.mars.reader.knowledge.module

import androidx.lifecycle.ViewModel
import com.mars.core.di.ViewModelKey
import com.mars.core.services.retrofit
import com.mars.reader.knowledge.api.KnowledgeApi
import com.mars.reader.knowledge.repo.KnowledgeRepository
import com.mars.reader.knowledge.vm.KnowledgeViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
class KnowledgeModule {


    @Provides
    fun provideKnowledgeApi(): KnowledgeApi {
        return retrofit.create(KnowledgeApi::class.java)
    }

    @Provides
    fun provideJnowledgeRepository(api: KnowledgeApi): KnowledgeRepository {
        return KnowledgeRepository(api)
    }

    @Provides
    @IntoMap
    @ViewModelKey(KnowledgeViewModel::class)
    fun provideKnowledgeViewModel(repo: KnowledgeRepository): ViewModel {
        return KnowledgeViewModel(repo)
    }
}