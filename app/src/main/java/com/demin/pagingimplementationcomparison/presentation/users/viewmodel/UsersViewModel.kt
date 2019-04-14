package com.demin.pagingimplementationcomparison.presentation.users.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.demin.domain.repository.UsersRepository
import com.demin.pagingimplementationcomparison.di.DI
import javax.inject.Inject

class UsersViewModel(private val usersRepository: UsersRepository) : ViewModel() {
    companion object {
        private const val LOG_TAG = "UsersViewModel"
    }

    val usersLiveData = MutableLiveData<UsersViewState>()

    fun loadUsers(searchQuery: String) {
        usersRepository.getUsers(searchQuery)
                .map<UsersViewState>(::Data)
                .toObservable()
                .startWith(Loading)
                .onErrorReturn { Error }
                .subscribe({ usersLiveData.value = it }, { th -> Log.e(LOG_TAG, "users loading", th)})
    }

    @Suppress("UNCHECKED_CAST")
    class Factory: ViewModelProvider.NewInstanceFactory() {
        @Inject
        internal lateinit var usersRepository: UsersRepository

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            DI.reposComponent.inject(this)
            return UsersViewModel(usersRepository) as T
        }
    }
}