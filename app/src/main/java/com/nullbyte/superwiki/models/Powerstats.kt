package com.nullbyte.superwiki.models


import com.google.gson.annotations.SerializedName

data class Powerstats(
    @SerializedName("intelligence")
    val intelligence: String, // 100
    @SerializedName("strength")
    val strength: String, // 26
    @SerializedName("speed")
    val speed: String, // 27
    @SerializedName("durability")
    val durability: String, // 50
    @SerializedName("power")
    val power: String, // 47
    @SerializedName("combat")
    val combat: String // 100
)