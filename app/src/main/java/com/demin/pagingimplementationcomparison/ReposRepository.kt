package com.demin.pagingimplementationcomparison

import com.demin.pagingimplementationcomparison.entity.Repository
import com.demin.pagingimplementationcomparison.network.ServiceFactory
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ReposRepository {

    private val serviceFactory = ServiceFactory()

    fun getRepositories(searchString: String): Single<List<Repository>> {
        return serviceFactory.gitHubService.searchRepos(
                searchString = searchString,
                pageSize = 10,
                pageNumber = 1
        )
                .map { it.items }
                .flatMapObservable { Observable.fromIterable(it) }
                .map { it.convert() }
                .toList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}