package com.nullbyte.superwiki.viewModels

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nullbyte.superwiki.base.BaseViewModel
import com.nullbyte.superwiki.constants.Constant
import com.nullbyte.superwiki.models.Profile
import com.nullbyte.superwiki.repository.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailedViewViewModel(application: Application) : BaseViewModel(application) {
    private val repository = Repository()
    private var showProgress: LiveData<Boolean>
    var superHeroDetails = MutableLiveData<Profile>()

    init {
        this.showProgress = repository.showProgress
    }

    fun getSuperheroDetailsById(id: String) {
        val api = repository.getApi(Constant.BASE_URL)

        api.getSuperHeroById(id)
            .enqueue(object : Callback<Profile> {
                override fun onResponse(
                    call: Call<Profile>,
                    response: Response<Profile>
                ) {
                    Log.i("Success", response.body()!!.name)
                    superHeroDetails.postValue(response.body())
                }

                override fun onFailure(call: Call<Profile>, t: Throwable) {
                    Log.i("Failure", t.message.toString())
                }

            })
    }
}