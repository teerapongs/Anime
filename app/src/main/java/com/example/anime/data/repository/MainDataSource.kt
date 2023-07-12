package com.example.anime.data.repository

import com.example.anime.data.remote.ApiService
import javax.inject.Inject

class MainDataSource @Inject constructor(
    private val service: ApiService
) {

    suspend fun getAnime() = service.getAnime()
}