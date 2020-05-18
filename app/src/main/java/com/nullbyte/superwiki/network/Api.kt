package com.nullbyte.superwiki.network

import com.nullbyte.superwiki.models.CompleteList
import com.nullbyte.superwiki.models.Profile
import com.nullbyte.superwiki.models.SearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {
    @GET("{name}")
    fun getSuperHeroByName(@Path("name") name: String): Call<SearchResponse>

    @GET("{id}")
    fun getSuperHeroById(@Path("id") searchedId: String): Call<Profile>

    @GET("{path}")
    fun getCompleteListOfSuperheroes(@Path("path") path: String): Call<List<CompleteList>>
}