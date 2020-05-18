package com.nullbyte.superwiki.viewModels

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nullbyte.superwiki.base.BaseViewModel
import com.nullbyte.superwiki.constants.Constant
import com.nullbyte.superwiki.models.CompleteList
import com.nullbyte.superwiki.models.SearchResponse
import com.nullbyte.superwiki.repository.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel(application: Application) : BaseViewModel(application) {
    private val repository = Repository()
    private var showProgress: LiveData<Boolean>
    var completeListDetail = MutableLiveData<List<CompleteList>>()
    var searchedResultDetail = MutableLiveData<SearchResponse>()

    init {
        this.showProgress = repository.showProgress
    }

    fun getCompleteListOfSuperheroes() {
        val api = repository.getApi(Constant.COMPLETE_LIST_URL)
        val path = "SuperheroList.json/"
        api.getCompleteListOfSuperheroes(path).enqueue(object : Callback<List<CompleteList>> {
            override fun onFailure(call: Call<List<CompleteList>>, t: Throwable) {
                Log.i("CompleteFailure", t.message.toString())
            }

            override fun onResponse(
                call: Call<List<CompleteList>>,
                response: Response<List<CompleteList>>
            ) {
                completeListDetail.postValue(response.body())
            }

        })
    }

    fun getSuperheroByName(searchedName: String) {
        val api = repository.getApi(Constant.BASE_URL + Constant.SEARCH_URL)

        api.getSuperHeroByName(searchedName).enqueue(object : Callback<SearchResponse> {
            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                Log.i("NameFailure", t.message.toString())
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