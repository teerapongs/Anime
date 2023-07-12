package com.example.anime.domain.model

data class AnimeDAO(
    val data: MutableList<AnimeData>?
)

data class AnimeData(
    val title: String?,
    val title_japanese: String?,
    val image: Image?,
    val status: String?,
    val episodes: Int?,
    val aired: Aired?,
    val duration: String?,
    val rating: String?,
    val score: Float?,
    val rank: Int?,
    val popularity: Int?,
    val synopsis: String?
)

data class Image(
    val jpg: JPG?
)

data class JPG(
    val image_url: String?,
    val small_image_url: String?,
    val large_image_url: String?
)

data class Aired(
    val from: String?,
    val to: String?,
    val string: String?
)
