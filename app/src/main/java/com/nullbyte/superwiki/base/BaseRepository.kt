package com.nullbyte.superwiki.base

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

open class BaseRepository{
    fun<T> Call<T>.makeCall(callback: CallBackKt<T>.() -> Unit) {
        val callBackKt = CallBackKt<T>()
        callback.invoke(callBackKt)
        this.enqueue(callBackKt)
    }

    class CallBackKt<T>: Callback<T> {
        var onResponseSuccess: ((Response<T>) -> Unit)? = null
        var onResponseFailure: ((error: String) -> Unit)? = null

        override fun onFailure(call: Call<T>, t: Throwable) {
            onResponseFailure?.invoke("onFailure")  //error handling
        }

        override fun onResponse(call: Call<T>, response: Response<T>) {
            if(response.isSuccessful)
                onResponseSuccess?.invoke(response)
            else{
                onResponseFailure?.invoke(response.errorBody().toString()) //error handling
            }
        }
    }
}