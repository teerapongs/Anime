package com.example.anime.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.anime.common.Resource
import com.example.anime.domain.use_case.GetAnimeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getAnimeUseCase: GetAnimeUseCase
) : ViewModel() {

    private val _anime = MutableLiveData<MainState>()
    val anime: LiveData<MainState> = _anime

    var onCallBack = MutableLiveData<Boolean>()

    init {
        getAnime()
        onCallBack.value = false
    }

    private fun getAnime() {
        getAnimeUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _anime.value = MainState(anime = result.data)
                }
                is Resource.Error -> {
                    _anime.value = MainState(error = result.message ?: "An unexpected error occurred")
                }
                is Resource.Loading -> {
                    _anime.value = MainState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getAmineAgain() {
        getAnime()
    }

    fun setOnCallBack() {
        onCallBack.value = onCallBack.value != true
    }
}