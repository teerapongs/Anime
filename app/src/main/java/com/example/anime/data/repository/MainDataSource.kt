package com.example.anime.data.repository

import com.example.anime.data.remote.ApiService

class MainDataSource(
    private val service: ApiService
) {

    suspend fun getAnime() = service.getAnime()
}