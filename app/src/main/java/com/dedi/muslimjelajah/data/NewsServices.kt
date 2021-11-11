package com.dedi.muslimjelajah.data

import com.dedi.muslimjelajah.BuildConfig
import com.dedi.muslimjelajah.data.entity.NewsResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface NewsServices {

    @Headers("X-Api-Key: $API_KEY")
    @GET("top-headlines?country=id&pageSize=100")
    suspend fun getBreakingNews(): NewsResponse

    companion object {

        const val BASE_URL = "https://newsapi.org/v2/"
        const val API_KEY = BuildConfig.NEWS_API_ACCESS_KEY

        private val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        private fun provideOkHttp() = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        fun create(baseUrl: String): NewsServices = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(provideOkHttp())
            .build()
            .create(NewsServices::class.java)
    }
}