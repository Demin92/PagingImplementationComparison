package com.demin.data.repository

import com.demin.data.network.ServiceFactory
import com.demin.domain.model.Repository
import com.demin.domain.repository.ReposRepository
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ReposDataRepository: ReposRepository {

    private val serviceFactory = ServiceFactory()

    override fun getRepositories(searchString: String): Single<List<Repository>> {
        return serviceFactory.gitHubService.searchRepos(
                searchString = searchString,
                pageSize = 10,
                pageNumber = 1
        )
                .map {
                    Thread.sleep(5000)
                    it.items }
                .flatMapObservable { Observable.fromIterable(it) }
                .map { it.convert() }
                .toList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}