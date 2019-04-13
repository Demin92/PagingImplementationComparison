package com.demin.pagingimplementationcomparison.di.repos

import com.demin.pagingimplementationcomparison.presentation.repos.viewmodel.ReposViewModel
import dagger.Component

@Component(modules = [(ReposModule::class)])
interface ReposComponent {
    fun inject(viewModel: ReposViewModel.Factory)
}