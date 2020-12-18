package com.example.topgithub.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Items(
    @SerializedName("repo")
    var repo: String?,
    @SerializedName("repo_link")
    var repo_link: String?,
    @SerializedName("desc")
    var desc: String?,
    @SerializedName("lang")
    var lang: String?,
    @SerializedName("stars")
    var stars: String?,
    @SerializedName("forks")
    var forks: String?,
    @SerializedName("added_stars")
    var added_stars: String?,
    @SerializedName("avatars")
    var avatars: List<String>?
) : Serializable