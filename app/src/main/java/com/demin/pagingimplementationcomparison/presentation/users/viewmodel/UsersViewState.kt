package com.demin.pagingimplementationcomparison.presentation.users.viewmodel

import com.demin.domain.model.User

sealed class UsersViewState
class Data(val users: List<User>): UsersViewState()
object Loading: UsersViewState()
object Error: UsersViewState()
