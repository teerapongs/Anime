package com.example.anime.domain.model

import com.google.gson.annotations.SerializedName

data class Images(
    val jpg: Jpg?
) {
    data class Jpg(
        @SerializedName("image_url")
        val imageUrl: String?,
        @SerializedName("small_image_url")
        val smallImageUrl: String?,
        @SerializedName("large_image_url")
        val largeImageUrl: String?
    )
}