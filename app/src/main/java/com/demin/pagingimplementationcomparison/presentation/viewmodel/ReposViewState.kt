package com.demin.pagingimplementationcomparison.presentation.viewmodel

import com.demin.pagingimplementationcomparison.entity.Repository

sealed class ReposViewState
class Data(val repos: List<Repository>): ReposViewState()
object Loading: ReposViewState()
object Error: ReposViewState()
