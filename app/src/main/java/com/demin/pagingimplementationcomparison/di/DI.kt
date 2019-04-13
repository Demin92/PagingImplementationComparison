package com.demin.pagingimplementationcomparison.di

import com.demin.pagingimplementationcomparison.di.repos.DaggerReposComponent
import com.demin.pagingimplementationcomparison.di.repos.ReposComponent

object DI {

    lateinit var reposComponent: ReposComponent

    fun init() {
        reposComponent = DaggerReposComponent.builder()
                .build()
    }
}