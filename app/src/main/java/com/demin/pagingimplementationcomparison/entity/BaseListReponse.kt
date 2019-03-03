package com.demin.pagingimplementationcomparison.entity

import com.google.gson.annotations.SerializedName

class BaseListReponse<T> (
        @SerializedName("items")
        val items: List<T>
)
