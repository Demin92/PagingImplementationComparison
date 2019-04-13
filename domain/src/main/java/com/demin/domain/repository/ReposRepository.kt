package com.demin.domain.repository

import com.demin.domain.model.Repository
import io.reactivex.Single

interface ReposRepository {
    fun getRepositories(searchString: String): Single<List<Repository>>
}