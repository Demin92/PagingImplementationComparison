package com.demin.pagingimplementationcomparison.presentation.repos.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.demin.data.repository.ReposDataRepository
import com.demin.domain.repository.ReposRepository
import com.demin.pagingimplementationcomparison.di.DI
import javax.inject.Inject

class ReposViewModel(private val reposRepository: ReposRepository) : ViewModel() {
    companion object {
        private const val LOG_TAG = "ReposViewModel"
    }

    val reposLiveData = MutableLiveData<ReposViewState>()

    fun loadRepos(searchQuery: String) {
        reposRepository.getRepositories(searchQuery)
                .map<ReposViewState>(::Data)
                .toObservable()
                .startWith(Loading)
                .onErrorReturn { Error }
                .subscribe({ reposLiveData.value = it }, {th -> Log.e(LOG_TAG, "repo", th)})
    }

    @Suppress("UNCHECKED_CAST")
    class Factory: ViewModelProvider.NewInstanceFactory() {
        @Inject
        internal lateinit var reposRepository: ReposRepository

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            DI.reposComponent.inject(this)
            return ReposViewModel(reposRepository) as T
        }
    }
}