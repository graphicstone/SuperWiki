package com.nullbyte.superwiki.viewModels

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nullbyte.superwiki.base.BaseViewModel
import com.nullbyte.superwiki.constants.Constant
import com.nullbyte.superwiki.models.SearchResponse
import com.nullbyte.superwiki.repository.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchResultViewModel(application: Application) : BaseViewModel(application) {
    private val repository = Repository()
    private var showProgress: LiveData<Boolean>
    var searchedResultDetail = MutableLiveData<SearchResponse>()

    init {
        this.showProgress = repository.showProgress
    }

    fun getSuperheroByName(searchedName: String) {
        val api = repository.getApi(Constant.BASE_URL + Constant.SEARCH_URL)

        api.getSuperHeroByName(searchedName).enqueue(object : Callback<SearchResponse> {
            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
//                searchedResultDetail.postValue()
            }

            override fun onResponse(
                call: Call<SearchResponse>,
                response: Response<SearchResponse>
            ) {
                searchedResultDetail.postValue(response.body())
            }

        })
    }
}
