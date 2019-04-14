package com.demin.pagingimplementationcomparison.presentation.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
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
import kotlinx.android.synthetic.main.fragment_users.*

class UsersFragment : Fragment() {

    companion object {
        fun newInstance(): UsersFragment {
            return UsersFragment()
        }
    }

    private lateinit var stateDelegator: StateDelegate<UsersViewDelegatorState>
    private val adapter = GroupAdapter<ViewHolder>()
    private lateinit var usersViewModel: UsersViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        usersViewModel = ViewModelProviders.of(this, UsersViewModel.Factory())[UsersViewModel::class.java]
        usersViewModel.usersLiveData.observe(this, Observer { state -> render(state) })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_users, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initView()
    }

    private fun initView() {
        initStateDelegator()
        initRecycler()
        search.setOnClickListener { usersViewModel.loadUsers(search_text.text.toString()) }
    }

    private fun initStateDelegator() {
        stateDelegator = StateDelegate(
                State(UsersViewDelegatorState.DATA, listOf(content)),
                State(UsersViewDelegatorState.EMPTY_PROGRESS, listOf(empty_loading), ShimmerLoadingStateChangeStrategy()),
                State(UsersViewDelegatorState.STUB, listOf(stub, retry)),
                State(UsersViewDelegatorState.ERROR, listOf(error, retry))
        )
    }

    private fun render(viewState: UsersViewState) {
        when (viewState) {
            is Loading -> {
                stateDelegator.currentState = UsersViewDelegatorState.EMPTY_PROGRESS
            }
            is Error -> {
                stateDelegator.currentState = UsersViewDelegatorState.ERROR
            }
            is Data -> {
                adapter.clear()
                if (viewState.users.isEmpty()) {
                    stateDelegator.currentState = UsersViewDelegatorState.STUB
                } else {
                    stateDelegator.currentState = UsersViewDelegatorState.DATA
                    viewState.users.map { UserItem(it) }.let { adapter.addAll(it) }
                }
            }
        }
    }

    private fun initRecycler() {
        with(content) {
            layoutManager = LinearLayoutManager(this@UsersFragment.context)
            adapter = this@UsersFragment.adapter
            addItemDecoration(DividerItemDecoration(this@UsersFragment.context, DividerItemDecoration.VERTICAL))
        }
    }

    inner class ShimmerLoadingStateChangeStrategy : ShowOnEnterGoneOnExitStrategy<UsersViewDelegatorState>() {
        private val blink = AnimationUtils.loadAnimation(context, R.anim.blink)

        override fun onStateEnter(state: State<UsersViewDelegatorState>, prevState: State<UsersViewDelegatorState>?) {
            super.onStateEnter(state, prevState)
            empty_loading.startAnimation(blink)
        }

        override fun onStateExit(state: State<UsersViewDelegatorState>, nextState: State<UsersViewDelegatorState>?) {
            super.onStateExit(state, nextState)
            empty_loading.clearAnimation()
        }
    }
}