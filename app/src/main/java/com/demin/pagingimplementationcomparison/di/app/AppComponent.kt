package com.demin.pagingimplementationcomparison.di.app

import com.demin.pagingimplementationcomparison.di.repos.UsersComponent
import dagger.Component

@Component(modules = [AppModule::class])
interface AppComponent {
    fun usersBuilder(): UsersComponent.Builder
}