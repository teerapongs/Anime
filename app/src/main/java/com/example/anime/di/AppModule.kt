package com.example.anime.di

import com.example.anime.common.Constants.BASE_URL
import com.example.anime.common.Constants.NETWORK_TIMEOUT
import com.example.anime.data.remote.ApiService
import com.example.anime.data.repository.MainDataSource
import com.example.anime.data.repository.MainRepositoryImpl
import com.example.anime.domain.repository.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providerOkHttpClient(): OkHttpClient {
        val okHttpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
        okHttpClientBuilder
            .certificatePinner(CertificatePinner.DEFAULT)
            .connectTimeout(NETWORK_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(NETWORK_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(NETWORK_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor { chain ->
                val original = chain.request()
                val requestBuilder = original.newBuilder()
                val newRequest: Request = requestBuilder.apply {
                    header("Content-Type", "application/json")
                }.build()
                chain.proceed(newRequest)
            }
        return okHttpClientBuilder.build()
    }

    @Provides
    @Singleton
    fun providerRetrofit(client: OkHttpClient): ApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
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