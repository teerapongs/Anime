package com.example.anime.data.repository

import com.example.anime.domain.model.Anime
import com.example.anime.domain.repository.MainRepository

class MainRepositoryImpl(
    private val dataSource: MainDataSource
): MainRepository {

    override suspend fun getAnime(): Anime {
        return dataSource.getAnime()
    }
}