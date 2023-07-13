package com.example.anime.ui.detail

import android.os.Bundle
import com.example.anime.base.BaseActivity
import com.example.anime.common.Constants.KEY_DATA
import com.example.anime.databinding.ActivityAnimeDetailsBinding
import com.example.anime.domain.model.AnimeData

class AnimeDetailsActivity : BaseActivity() {

    private var _binding: ActivityAnimeDetailsBinding? = null
    private val binding get() = _binding
    private var data: AnimeData? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityAnimeDetailsBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        intent.getStringExtra(KEY_DATA)?.apply {
            data = mGson.fromJson(this, AnimeData::class.java)
        }
    }

    override fun onResume() {
        super.onResume()
        intent.removeExtra(KEY_DATA)
        updateUI()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun updateUI() {
        data?.let {
            binding?.apply {
                anime = it
                image = it.images?.jpg
            }
        }
    }
}