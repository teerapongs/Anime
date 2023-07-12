package com.example.anime.di

import com.example.anime.common.Constants
import com.example.anime.data.remote.ApiService
import com.example.anime.data.repository.MainDataSource
import com.example.anime.data.repository.MainRepositoryImpl
import com.example.anime.domain.repository.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providerAPI(): ApiService {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun providerMainRepository(data: MainDataSource): MainRepository {
        return MainRepositoryImpl(data)
    }
}