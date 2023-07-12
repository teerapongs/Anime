package com.example.anime.ui.detail

import android.os.Bundle
import com.example.anime.base.BaseActivity
import com.example.anime.databinding.ActivityAnimeDetailsBinding

class AnimeDetailsActivity : BaseActivity() {

    private lateinit var binding: ActivityAnimeDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnimeDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}