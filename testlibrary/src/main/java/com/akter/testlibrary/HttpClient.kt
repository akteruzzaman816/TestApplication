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
                    .baseUrl("https://gist.githubusercontent.com/")
                    .build()
                    .create(RetrofitApi::class.java)
            return instance as RetrofitApi
        }
    }
}

interface RetrofitApi {
    @GET("poepanda/d23d18e6ad897e34e7a696122ce0ca5a/raw/9bcd11a2a96080dba4a9c43c9428fc4c266105c9/fake-api-banner.json")
    suspend fun getData() : Response<AdModelResponse>

}