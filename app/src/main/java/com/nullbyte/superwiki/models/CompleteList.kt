package com.nullbyte.superwiki.models


import com.google.gson.annotations.SerializedName

data class CompleteList(
    @SerializedName("id")
    val id: Int, // 731
    @SerializedName("name")
    val name: String, // Zoom
    @SerializedName("fullName")
    val fullName: String, // Hunter Zolomon
    @SerializedName("publisher")
    val publisher: String, // DC Comics
    @SerializedName("profileImage")
    val profileImage: String // https://cdn.rawgit.com/akabab/superhero-api/0.2.0/api/images/sm/731-zoom.jpg
)