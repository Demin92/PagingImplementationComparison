package com.demin.pagingimplementationcomparison.presentation.users.recycler

import com.bumptech.glide.Glide
import com.demin.domain.model.User
import com.demin.pagingimplementationcomparison.R
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.item_repository.view.*

class UserItem(private val user: User) : Item<ViewHolder>() {
    override fun getLayout() = R.layout.item_repository

    override fun bind(viewHolder: ViewHolder, position: Int) {
        with(viewHolder.itemView) {
            Glide.with(context)
                    .load(user.avatarUrl)
                    .into(user_avatar)
            user_login.text = user.login
            user_scores.text = resources.getString(R.string.score, user.score)
        }
    }
}