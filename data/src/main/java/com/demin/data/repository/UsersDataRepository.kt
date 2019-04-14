package com.demin.data.repository

import com.demin.data.network.ServiceFactory
import com.demin.domain.model.User
import com.demin.domain.repository.UsersRepository
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class UsersDataRepository: UsersRepository {

    private val serviceFactory = ServiceFactory()

    override fun getUsers(searchString: String): Single<List<User>> {
        return serviceFactory.gitHubService.searchUsers(
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