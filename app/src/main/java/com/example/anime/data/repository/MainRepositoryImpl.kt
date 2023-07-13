package com.example.anime.data.repository

import com.example.anime.domain.model.Anime
import com.example.anime.domain.repository.MainRepository
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor (
    private val dataSource: MainDataSource
): MainRepository {
    override suspend fun getAnime(): Anime {
        return dataSource.getAnime()
    }
}