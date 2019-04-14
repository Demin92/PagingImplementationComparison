package com.demin.data.model.entity

import com.demin.data.network.Dto
import com.demin.data.network.getOrDie
import com.demin.domain.model.User
import com.google.gson.annotations.SerializedName

class NWUser(
        @SerializedName("login")
        val login: String?,

        @SerializedName("avatar_url")
        val avatarUrl: String?,

        @SerializedName("score")
        val score: Double?
) : Dto<User> {
    override fun convert(): User {
        return User(
                login = getOrDie(login, "NWUser/login"),
                avatarUrl = getOrDie(avatarUrl, "NWUser/avatarUrl"),
                score = getOrDie(score, "NWUser/score")
        )
    }
}