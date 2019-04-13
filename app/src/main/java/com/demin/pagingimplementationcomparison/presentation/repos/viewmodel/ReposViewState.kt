package com.demin.pagingimplementationcomparison.presentation.repos.viewmodel

import com.demin.domain.model.Repository

sealed class ReposViewState
class Data(val repos: List<Repository>): ReposViewState()
object Loading: ReposViewState()
object Error: ReposViewState()
