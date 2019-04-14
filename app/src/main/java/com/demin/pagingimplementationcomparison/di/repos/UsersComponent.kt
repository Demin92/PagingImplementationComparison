package com.demin.pagingimplementationcomparison.di.repos

import com.demin.pagingimplementationcomparison.presentation.users.viewmodel.UsersViewModel
import dagger.Subcomponent

@Subcomponent(modules = [(UsersModule::class)])
interface UsersComponent {

    @Subcomponent.Builder
    interface Builder{
        fun build(): UsersComponent
    }

    fun inject(viewModel: UsersViewModel.Factory)
}