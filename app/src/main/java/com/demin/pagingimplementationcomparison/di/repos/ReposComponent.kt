package com.demin.pagingimplementationcomparison.di.repos

import com.demin.pagingimplementationcomparison.presentation.users.viewmodel.UsersViewModel
import dagger.Component

@Component(modules = [(ReposModule::class)])
interface ReposComponent {
    fun inject(viewModel: UsersViewModel.Factory)
}