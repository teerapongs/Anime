package com.example.anime.ui.main

import com.example.anime.domain.model.AnimeDAO

data class MainState(
    val isLoading: Boolean = false,
    val anime: AnimeDAO? = null,
    val error: String = ""
)