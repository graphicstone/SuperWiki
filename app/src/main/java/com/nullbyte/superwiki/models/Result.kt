package com.nullbyte.superwiki.models


import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("id")
    val id: String, // 69
    @SerializedName("name")
    val name: String, // Batman
    @SerializedName("powerstats")
    val powerstats: Powerstats,
    @SerializedName("biography")
    val biography: Biography,
    @SerializedName("appearance")
    val appearance: Appearance,
    @SerializedName("work")
    val work: Work,
    @SerializedName("connections")
    val connections: Connections,
    @SerializedName("image")
    val image: Image
)