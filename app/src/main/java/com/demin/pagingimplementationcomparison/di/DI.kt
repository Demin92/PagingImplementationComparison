package com.demin.pagingimplementationcomparison.di

import com.demin.pagingimplementationcomparison.di.app.AppComponent
import com.demin.pagingimplementationcomparison.di.app.DaggerAppComponent
object DI {

    lateinit var appComponent: AppComponent

    fun init() {
        appComponent = DaggerAppComponent.builder()
                .build()
    }

    fun getUsersComponent() = appComponent.usersBuilder().build()

}