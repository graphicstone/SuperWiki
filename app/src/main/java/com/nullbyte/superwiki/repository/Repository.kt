package com.nullbyte.superwiki.repository

import androidx.lifecycle.MutableLiveData
import com.nullbyte.superwiki.constants.Constant
import com.nullbyte.superwiki.network.Api
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class Repository() {
    val showProgress = MutableLiveData<Boolean>()

    fun getApi(URL : String): Api {
        val retrofit = Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(Api::class.java)
    }
}