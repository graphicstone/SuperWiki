package com.nullbyte.superwiki.models


import com.google.gson.annotations.SerializedName

data class Biography(
    @SerializedName("full-name")
    val fullName: String, // Bruce Wayne
    @SerializedName("alter-egos")
    val alterEgos: String, // No alter egos found.
    @SerializedName("aliases")
    val aliases: List<String>,
    @SerializedName("place-of-birth")
    val placeOfBirth: String, // Crest Hill, Bristol Township; Gotham County
    @SerializedName("first-appearance")
    val firstAppearance: String, // Detective Comics #27
    @SerializedName("publisher")
    val publisher: String, // DC Comics
    @SerializedName("alignment")
    val alignment: String // good
)