package com.demin.pagingimplementationcomparison.presentation

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.animation.AnimationUtils
import android.widget.LinearLayout
import com.demin.pagingimplementationcomparison.R
import com.demin.pagingimplementationcomparison.ReposRepository
import com.demin.pagingimplementationcomparison.entity.Repository
import com.demin.pagingimplementationcomparison.presentation.recycler.RepoItem
import com.redmadrobot.lib.sd.base.ShowOnEnterGoneOnExitStrategy
import com.redmadrobot.lib.sd.base.State
import com.redmadrobot.lib.sd.base.StateDelegate
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val reposRepository = ReposRepository()
    private lateinit var stateDelegator: StateDelegate<GitReposViewState>
    private val adapter = GroupAdapter<ViewHolder>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {
        initStateDelegator()
        initRecycler()
        search.setOnClickListener {
            setState(GitReposViewState.EMPTY_PROGRESS)
            reposRepository.getRepositories(search_text.text.toString())
                    .subscribe({ repos ->
                        if (repos.isEmpty()) {
                            setState(GitReposViewState.STUB)
                        } else {
                            setState(GitReposViewState.DATA, repos)
                        }
                    }, {
                        setState(GitReposViewState.ERROR)
                    })
        }
    }

    private fun initStateDelegator() {
        stateDelegator = StateDelegate(
                State(GitReposViewState.DATA, listOf(content)),
                State(GitReposViewState.EMPTY_PROGRESS, listOf(empty_loading), ShimmerLoadingStateChangeStrategy()),
                State(GitReposViewState.STUB, listOf(stub, retry)),
                State(GitReposViewState.ERROR, listOf(error, retry))

        )
    }

    private fun setState(state: GitReposViewState, repos: List<Repository>? = null) {
        stateDelegator.currentState = state
        if (state == GitReposViewState.DATA) {
            repos?.map { RepoItem(it) }?.let { adapter.addAll(it) }
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
