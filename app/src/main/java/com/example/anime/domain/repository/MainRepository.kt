package com.example.anime.domain.repository

import com.example.anime.domain.model.Anime

interface MainRepository {

    suspend fun getAnime(): Anime
}