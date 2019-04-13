package com.demin.pagingimplementationcomparison.di.repos

import com.demin.data.repository.ReposDataRepository
import com.demin.domain.repository.ReposRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class ReposModule {

    @Provides
    fun provideReposRepository(): ReposRepository {
        return ReposDataRepository()
    }
}