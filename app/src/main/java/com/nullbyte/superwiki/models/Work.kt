package com.nullbyte.superwiki.models


import com.google.gson.annotations.SerializedName

data class Work(
    @SerializedName("occupation")
    val occupation: String, // Businessman
    @SerializedName("base")
    val base: String // Batcave, Stately Wayne Manor, Gotham City; Hall of Justice, Justice League Watchtower
)