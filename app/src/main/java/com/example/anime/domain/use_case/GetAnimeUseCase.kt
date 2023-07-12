package com.example.anime.domain.use_case

import com.example.anime.common.Resource
import com.example.anime.domain.model.AnimeDAO
import com.example.anime.domain.repository.MainRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetAnimeUseCase @Inject constructor(
    private val repository: MainRepository
) {
    operator fun invoke(): Flow<Resource<AnimeDAO>> = flow {
        try {
            emit(Resource.Loading())
            val anime = repository.getAnime()
            emit(Resource.Success(anime))
        } catch (e: HttpException) {
            emit(Resource.Error(message = e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}