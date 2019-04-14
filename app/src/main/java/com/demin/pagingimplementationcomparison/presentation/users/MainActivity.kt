package com.demin.pagingimplementationcomparison.presentation.users

import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.demin.pagingimplementationcomparison.R
import com.demin.pagingimplementationcomparison.presentation.users.recycler.UserItem
import com.demin.pagingimplementationcomparison.presentation.users.viewmodel.*
import com.redmadrobot.lib.sd.base.ShowOnEnterGoneOnExitStrategy
import com.redmadrobot.lib.sd.base.State
import com.redmadrobot.lib.sd.base.StateDelegate
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var stateDelegator: StateDelegate<GitReposViewState>
    private val adapter = GroupAdapter<ViewHolder>()
    private lateinit var usersViewModel: UsersViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        usersViewModel = ViewModelProviders.of(this, UsersViewModel.Factory())[UsersViewModel::class.java]
        usersViewModel.reposLiveData.observe(this, Observer { state -> render(state) })
        initView()
    }

    private fun initView() {
        initStateDelegator()
        initRecycler()
        search.setOnClickListener { usersViewModel.loadRepos(search_text.text.toString()) }
    }

    private fun initStateDelegator() {
        stateDelegator = StateDelegate(
                State(GitReposViewState.DATA, listOf(content)),
                State(GitReposViewState.EMPTY_PROGRESS, listOf(empty_loading), ShimmerLoadingStateChangeStrategy()),
                State(GitReposViewState.STUB, listOf(stub, retry)),
                State(GitReposViewState.ERROR, listOf(error, retry))
        )
    }

    private fun render(viewState: ReposViewState) {
        when (viewState) {
            is Loading -> stateDelegator.currentState = GitReposViewState.EMPTY_PROGRESS
            is Error -> stateDelegator.currentState = GitReposViewState.ERROR
            is Data -> {
                if (viewState.repos.isEmpty()) {
                    stateDelegator.currentState = GitReposViewState.STUB
                } else {
                    stateDelegator.currentState = GitReposViewState.DATA
                    viewState.repos.map { UserItem(it) }.let { adapter.addAll(it) }
                }
            }
        }
    }

    private fun initRecycler() {
        with(content) {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.adapter
            addItemDecoration(DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL))
        }
    }

    inner class ShimmerLoadingStateChangeStrategy : ShowOnEnterGoneOnExitStrategy<GitReposViewState>() {
        private val blink = AnimationUtils.loadAnimation(applicationContext, R.anim.blink)

        override fun onStateEnter(state: State<GitReposViewState>, prevState: State<GitReposViewState>?) {
            super.onStateEnter(state, prevState)
            empty_loading.startAnimation(blink)
        }

        override fun onStateExit(state: State<GitReposViewState>, nextState: State<GitReposViewState>?) {
            super.onStateExit(state, nextState)
            empty_loading.clearAnimation()
        }
    }
}
