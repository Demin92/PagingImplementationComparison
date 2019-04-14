package com.demin.pagingimplementationcomparison.di.app

import com.demin.data.network.ServiceFactory
import com.demin.data.repository.UsersDataRepository
import com.demin.domain.repository.UsersRepository
import dagger.Module
import dagger.Provides


@Module
class AppModule {

    @Provides
    fun provideServiceFactory(): ServiceFactory {
        return ServiceFactory()
    }
}