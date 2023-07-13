package com.example.anime.ui.main

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.anime.base.BaseActivity
import com.example.anime.common.Constants.KEY_DATA
import com.example.anime.databinding.ActivityMainBinding
import com.example.anime.domain.model.Anime
import com.example.anime.domain.model.AnimeData
import com.example.anime.extension.hide
import com.example.anime.extension.navigate
import com.example.anime.extension.show
import com.example.anime.ui.detail.AnimeDetailsActivity
import com.example.anime.ui.main.adapter.MainAdapter
import com.example.anime.ui.anim.ProgressBarAnimation
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding
    private val mainAdapter: MainAdapter by lazy { MainAdapter() }
    private val mainViewModel: MainViewModel? by viewModels()
    private var animeDAO: Anime? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setUpRecyclerView()
        setUpObserver()
    }

    override fun onResume() {
        super.onResume()
        mainViewModel?.onCallBack?.value?.run {
            if (this) {
                mainViewModel?.apply {
                    setOnCallBack()
                    getAmineAgain()
                }
            }
        }
    }

    override fun onPause() {
        super.onPause()
        mainViewModel?.setOnCallBack()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setUpRecyclerView() {
        binding?.animeRecyclerView?.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = mainAdapter
        }
    }

    private fun setUpObserver() {
        mainViewModel?.anime?.observe(this, Observer { response ->
            val state = response ?: return@Observer

            state.anime?.let { anime ->
                animeDAO = anime
                updateUI()
            }
            if (state.error.isNotBlank()) {
                hideProgressBar()
                uiShowError(state.error)
            }
            if (state.isLoading) {
                showProgressBar()
            }
        })
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun updateUI() {
        if (animeDAO != null){
            hideProgressBar()
            mainAdapter.apply {
                setItems(animeDAO?.data)
                setOnItemClickListener { data ->
                    onStartActivityAnimeDetails(data)
                }
                notifyDataSetChanged()
            }
        }
    }

    private fun showProgressBar() {
        binding?.loadingProgressBar?.apply {
            val anim= ProgressBarAnimation(this, 0F, 100F)
            anim.duration = 1000
            animation = animation
            show()
        }
    }

    private fun hideProgressBar() {
        binding?.loadingProgressBar?.hide()
    }

    private fun onStartActivityAnimeDetails(data: AnimeData?) {
        navigate<AnimeDetailsActivity> {
            putExtra(KEY_DATA, mGson.toJson(data))
        }
    }
}