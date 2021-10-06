package com.dedi.muslimjelajah.data

import com.dedi.muslimjelajah.data.entity.SurahResponse
import com.dedi.muslimjelajah.domain.entity.Ayah
import com.dedi.muslimjelajah.domain.entity.Surah
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Services {

    @GET("99c279bb173a6e28359c")
    suspend fun surah(): SurahResponse

    @GET("99c279bb173a6e28359c/surat/{nomor}")
    suspend fun ayah(
        @Path("nomor") nomor: Int
    ): List<Ayah>


    companion object {
        private val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.NONE
        }

        private fun provideOkHttp() = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        fun create(baseUrl: String): Services = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(provideOkHttp())
            .build()
            .create(Services::class.java)
    }
}