package com.demin.data.model.response

import com.google.gson.annotations.SerializedName

class BaseListReponse<T> (
        @SerializedName("items")
        val items: List<T>
)
