package com.example.topgithub.model

import com.google.gson.annotations.SerializedName

data class GithubResponse(
    @SerializedName("count")
    var count: Int?,
    @SerializedName("items")
    var items: List<Items>?
)
