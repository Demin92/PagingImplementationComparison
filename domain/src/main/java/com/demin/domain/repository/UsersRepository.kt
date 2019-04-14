package com.demin.domain.repository

import com.demin.domain.model.User
import io.reactivex.Single

interface UsersRepository {
    fun getRepositories(searchString: String): Single<List<User>>
}