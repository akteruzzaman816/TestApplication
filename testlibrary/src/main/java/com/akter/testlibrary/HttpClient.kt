package com.akter.testlibrary

import com.akter.testlibrary.model.AdModelResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

class HttpClient {
    companion object {
        private var instance : RetrofitApi? = null
        @Synchronized
        fun getInstance(): RetrofitApi {
            if (instance == null)
                instance = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("https://raw.githubusercontent.com/")
                    .build()
                    .create(RetrofitApi::class.java)
            return instance as RetrofitApi
        }
    }
}

interface RetrofitApi {
    @GET("akteruzzaman816/TestApplication/master/fake_api.json")
    suspend fun getData() : Response<AdModelResponse>

}