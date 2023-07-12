package com.example.anime.data.remote

import com.example.anime.common.Constants
import com.example.anime.domain.model.AnimeDAO
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiService {

    @GET(Constants.ANIME)
    suspend fun getAnime(): AnimeDAO

//    companion object {
//        fun providerAPI(): ApiService {
//            return  Retrofit.Builder()
//                .baseUrl(Constants.BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build()
//                .create(ApiService::class.java)
//        }
//    }
}