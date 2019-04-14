package com.demin.pagingimplementationcomparison.di.repos

import com.demin.data.repository.UsersDataRepository
import com.demin.domain.repository.UsersRepository
import dagger.Module
import dagger.Provides


@Module
class ReposModule {

    @Provides
    fun provideReposRepository(): UsersRepository {
        return UsersDataRepository()
    }
}