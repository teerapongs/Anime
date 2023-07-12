package com.example.anime

import android.app.Application
import androidx.lifecycle.createSavedStateHandle
import com.example.anime.data.remote.ApiService
import com.example.anime.data.repository.MainDataSource
import com.example.anime.data.repository.MainRepositoryImpl
import com.example.anime.di.AppModule
import com.example.anime.domain.repository.MainRepository
import com.example.anime.domain.use_case.GetAnimeUseCase
import com.example.anime.ui.main.MainViewModel
import dagger.hilt.android.HiltAndroidApp
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

@HiltAndroidApp
class AnimeApplication: Application()
/*
{

    override fun onCreate() {
        super.onCreate()
//        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidLogger()
            androidContext(this@AnimeApplication)
            val mainModule = module {
//                single { ApiService.providerAPI() }
                single { MainDataSource(get()) }
                single<MainRepository> { MainRepositoryImpl(get()) }
                single { GetAnimeUseCase(get()) }
                viewModel { MainViewModel(get()) }
            }
            modules(mainModule)
        }
    }
}*/
