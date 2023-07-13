package com.example.anime.data.remote

import com.example.anime.common.Constants.ANIME
import com.example.anime.domain.model.Anime
import retrofit2.http.GET

interface ApiService {

    @GET(ANIME)
    suspend fun getAnime(): Anime

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