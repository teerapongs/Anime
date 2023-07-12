package com.example.anime.data.repository

import com.example.anime.domain.model.AnimeDAO
import com.example.anime.domain.repository.MainRepository
import javax.inject.Inject


class MainRepositoryImpl @Inject constructor (
    private val dataSource: MainDataSource
): MainRepository {

    override suspend fun getAnime(): AnimeDAO {
        return dataSource.getAnime()
    }
}