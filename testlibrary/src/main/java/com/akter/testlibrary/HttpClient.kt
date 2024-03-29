package com.akter.testlibrary

import com.akter.testlibrary.model.AdModelResponse
import com.akter.testlibrary.model.ModelAdActionRequest
import com.akter.testlibrary.model.ModelAdRequest
import com.akter.testlibrary.model.ModelAdResponse
import com.akter.testlibrary.utils.AdfinixAdType
import com.akter.testlibrary.utils.TestLibraryConstants
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

class HttpClient {
    companion object {
        private var instance : RetrofitApi? = null
        @Synchronized
        fun getInstance(): RetrofitApi {
            if (instance == null)
                instance = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(
                        when (Adfinix.adType) {
                            AdfinixAdType.LIVE -> TestLibraryConstants.LIVE_SERVER
                            AdfinixAdType.TEST -> TestLibraryConstants.TEST_SERVER
                            else -> throw Exception("please do Adfinix initial setup")
                        }
                    )
                    .client(
                        OkHttpClient.Builder()
                        .addInterceptor(
                            HttpLoggingInterceptor().also {
                                it.level = HttpLoggingInterceptor.Level.BODY
                            }
                        ).build()
                    )
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

    @POST("v1/view")
    suspend fun adViewed(
        @Body requestBody: ModelAdActionRequest
    ) : Response<ResponseBody>

    @POST("v1/click")
    suspend fun adClicked(
        @Body requestBody: ModelAdActionRequest
    ) : Response<ResponseBody>

}