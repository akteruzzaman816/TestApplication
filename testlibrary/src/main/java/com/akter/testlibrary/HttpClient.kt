package com.akter.testlibrary

import com.akter.testlibrary.model.AdModelResponse
import com.akter.testlibrary.model.ModelAdRequest
import com.akter.testlibrary.model.ModelAdResponse
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

class HttpClient {
    companion object {
        private var instance : RetrofitApi? = null
        @Synchronized
        fun getInstance(): RetrofitApi {
            if (instance == null)
                instance = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("https://bangladesh-staging.adfinix.com/")
                    .build()
                    .create(RetrofitApi::class.java)
            return instance as RetrofitApi
        }
    }
}

interface RetrofitApi {
    @GET("akteruzzaman816/TestApplication/master/fake_api.json")
    suspend fun getData() : Response<AdModelResponse>

    @POST("v1/")
    suspend fun getAdData(
        @Body requestBody: ModelAdRequest
    ) : Response<ModelAdResponse>

}