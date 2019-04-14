package com.demin.data.network.api

import com.demin.data.model.entity.NWUser
import com.demin.data.model.response.BaseListReponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface GitHubService {
    @GET("search/users?sort=score")
    fun searchRepos(
            @Query("q") searchString: String,
            @Query("page") pageNumber: Int,
            @Query("per_page") pageSize: Int
    ): Single<BaseListReponse<NWUser>>
}