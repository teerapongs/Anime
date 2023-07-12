package com.example.anime.ui.main

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.anime.base.BaseActivity
import com.example.anime.databinding.ActivityMainBinding
import com.example.anime.ui.main.adapter.MainAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainAdapter: MainAdapter by lazy { MainAdapter() }
    private val mainViewModel: MainViewModel? by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpRecyclerView()
        mainViewModel?.anime?.observe(this, Observer { response ->
            val result = response ?: return@Observer
            println("####result: $result")
        })
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setUpRecyclerView() {
        binding.animeRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = mainAdapter
        }
        mainAdapter.notifyDataSetChanged()
    }
}