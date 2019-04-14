package com.demin.domain.repository

import com.demin.domain.model.User
import io.reactivex.Single

interface UsersRepository {
    fun getUsers(searchString: String): Single<List<User>>
}