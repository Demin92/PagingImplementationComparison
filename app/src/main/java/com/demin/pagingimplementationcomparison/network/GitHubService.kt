package com.demin.pagingimplementationcomparison.network

import com.demin.pagingimplementationcomparison.entity.BaseListReponse
import com.demin.pagingimplementationcomparison.entity.NWRepository
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface GitHubService {
    @GET("search/repositories?sort=stars")
    fun searchRepos(
            @Query("q") searchString: String,
            @Query("page") pageNumber: Int,
            @Query("per_page") pageSize: Int
    ): Single<BaseListReponse<NWRepository>>
}