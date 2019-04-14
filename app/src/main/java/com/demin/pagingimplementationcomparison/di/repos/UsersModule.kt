package com.demin.pagingimplementationcomparison.di.repos

import com.demin.data.network.ServiceFactory
import com.demin.data.repository.UsersDataRepository
import com.demin.domain.repository.UsersRepository
import dagger.Module
import dagger.Provides

@Module
class UsersModule {

    @Provides
    fun provideReposRepository(serviceFactory: ServiceFactory): UsersRepository {
        return UsersDataRepository(serviceFactory)
    }
}