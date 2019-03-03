package com.demin.pagingimplementationcomparison.presentation.recycler

import com.demin.pagingimplementationcomparison.R
import com.demin.pagingimplementationcomparison.entity.Repository
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.item_repository.view.*

class RepoItem(private val repo: Repository) : Item<ViewHolder>() {
    override fun getLayout() = R.layout.item_repository

    override fun bind(viewHolder: ViewHolder, position: Int) {
        with(viewHolder.itemView) {
            repository_name.text = repo.name
            stars_count.text = resources.getString(R.string.stars_count, repo.stars)
            forks_count.text = resources.getString(R.string.forks_count, repo.forks)
            issues_count.text = resources.getString(R.string.issues_count, repo.issues)
        }
    }
}