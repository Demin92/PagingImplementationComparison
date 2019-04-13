package com.demin.data.model.entity

import com.demin.data.network.Dto
import com.demin.data.network.getOrDie
import com.demin.domain.model.Repository
import com.google.gson.annotations.SerializedName

class NWRepository(
        @SerializedName("name")
        val name: String?,

        @SerializedName("stargazers_count")
        val stars: Int?,

        @SerializedName("forks")
        val forks: Int?,

        @SerializedName("open_issues")
        val issues: Int?
) : Dto<Repository> {
    override fun convert(): Repository {
        return Repository(
                name = getOrDie(name, "NWRepository/name"),
                stars = getOrDie(stars, "NWRepository/stars"),
                forks = getOrDie(forks, "NWRepository/forks"),
                issues = getOrDie(issues, "NWRepository/issues")
        )
    }
}