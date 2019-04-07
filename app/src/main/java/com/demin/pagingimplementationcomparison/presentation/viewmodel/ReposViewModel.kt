package com.demin.pagingimplementationcomparison.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.demin.pagingimplementationcomparison.ReposRepository

class ReposViewModel : ViewModel() {
    companion object {
        private const val LOG_TAG = "ReposViewModel"
    }

    private val reposRepository = ReposRepository()

    val reposLiveData = MutableLiveData<ReposViewState>()

    fun loadRepos(searchQuery: String) {
        reposRepository.getRepositories(searchQuery)
                .map<ReposViewState>(::Data)
                .toObservable()
                .startWith(Loading)
                .onErrorReturn { Error }
                .subscribe({ reposLiveData.value = it }, {th -> Log.e(LOG_TAG, "repo", th)})
    }
}