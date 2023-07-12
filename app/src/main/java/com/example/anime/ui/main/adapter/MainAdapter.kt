package com.example.anime.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.anime.databinding.ContentListAnimeBinding
import com.example.anime.domain.model.AnimeData

class MainAdapter : RecyclerView.Adapter<MainViewHolder>() {

    private var items: MutableList<AnimeData>? = mutableListOf()

    fun setItems(items: MutableList<AnimeData>?) {
        this.items = items
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MainViewHolder(
        ContentListAnimeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun getItemCount(): Int = items?.size ?: 0

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.onBindData(items?.get(position))
    }
}