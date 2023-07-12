package com.example.anime.ui.main.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.anime.databinding.ContentListAnimeBinding
import com.example.anime.extension.loadImage
import com.example.anime.domain.model.AnimeData

class MainViewHolder(
    private val binding: ContentListAnimeBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun onBindData(data: AnimeData?) {
        binding.animeImageView loadImage data?.image?.jpg?.small_image_url
        binding.animeTextView.text = data?.title
    }
}