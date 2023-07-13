package com.example.anime.domain.model

import com.google.gson.annotations.SerializedName

data class AnimeData(
    val aired: Aired?,
    val duration: String?,
    val episodes: Int?,
    val images: Images?,
    val popularity: Int?,
    val rank: Int?,
    val rating: String?,
    val score: Double?,
    val status: String?,
    val synopsis: String?,
    val title: String?,
    @SerializedName("title_japanese")
    val titleJapanese: String?
)