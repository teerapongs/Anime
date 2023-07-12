package com.example.anime.domain.repository

import com.example.anime.domain.model.AnimeDAO

interface MainRepository {

    suspend fun getAnime(): AnimeDAO
}