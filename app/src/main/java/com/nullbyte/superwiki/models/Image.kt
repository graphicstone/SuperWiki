package com.nullbyte.superwiki.models


import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("url")
    val url: String // https://www.superherodb.com/pictures2/portraits/10/100/639.jpg
)