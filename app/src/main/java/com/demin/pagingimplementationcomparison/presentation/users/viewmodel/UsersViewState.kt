package com.demin.pagingimplementationcomparison.presentation.users.viewmodel

import com.demin.domain.model.User

sealed class ReposViewState
class Data(val repos: List<User>): ReposViewState()
object Loading: ReposViewState()
object Error: ReposViewState()
