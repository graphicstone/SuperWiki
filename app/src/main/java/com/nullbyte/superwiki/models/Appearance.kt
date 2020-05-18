package com.nullbyte.superwiki.models


import com.google.gson.annotations.SerializedName

data class Appearance(
    @SerializedName("gender")
    val gender: String, // Male
    @SerializedName("race")
    val race: String, // Human
    @SerializedName("height")
    val height: List<String>,
    @SerializedName("weight")
    val weight: List<String>,
    @SerializedName("eye-color")
    val eyeColor: String, // blue
    @SerializedName("hair-color")
    val hairColor: String // black
)