package com.nullbyte.superwiki.models


import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("response")
    val response: String, // success
    @SerializedName("results-for")
    val resultsFor: String, // batman
    @SerializedName("results")
    val results: List<Result>
)