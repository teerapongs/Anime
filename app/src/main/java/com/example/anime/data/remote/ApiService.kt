package com.example.anime.data.remote

import com.example.anime.common.Constants
import com.example.anime.common.Constants.ANIME
import com.example.anime.common.Constants.BASE_URL
import com.example.anime.domain.model.Anime
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit

interface ApiService {

    @GET(ANIME)
    suspend fun getAnime(): Anime

    companion object {
        fun providerAPI(): ApiService {
            val okHttpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
            okHttpClientBuilder
                .certificatePinner(CertificatePinner.DEFAULT)
                .connectTimeout(Constants.NETWORK_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(Constants.NETWORK_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(Constants.NETWORK_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor { chain ->
                    val original = chain.request()
                    val requestBuilder = original.newBuilder()
                    val newRequest: Request = requestBuilder.apply {
                        header("Content-Type", "application/json")
                    }.build()
                    chain.proceed(newRequest)
                }
            okHttpClientBuilder.build()

            return  Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
        }
    }
}