package com.example.anime.ui.main

import com.example.anime.domain.model.Anime

data class MainState(
    val isLoading: Boolean = false,
    val anime: Anime? = null,
    val error: String = ""
)