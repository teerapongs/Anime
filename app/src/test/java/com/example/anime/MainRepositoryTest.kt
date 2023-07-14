package com.example.anime

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.anime.domain.model.Anime
import com.example.anime.domain.model.AnimeData
import com.example.anime.domain.repository.MainRepository
import com.example.anime.domain.use_case.GetAnimeUseCase
import com.example.anime.ui.main.MainViewModel
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MainRepositoryTest {

    @Mock
    private lateinit var repository: MainRepository

    @Mock
    private lateinit var getAnimeUseCase: GetAnimeUseCase

    @Mock
    private lateinit var viewModel: MainViewModel

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val mockData: MutableList<AnimeData> = mutableListOf<AnimeData>().apply {
        add(AnimeData(
            aired = null,
            duration = "",
            episodes = 24,
            images = null,
            popularity = 1371,
            rank = 1702,
            rating = "R - 17+ (violence & profanity)" ,
            score = 7.51,
            status = "Finished Airing",
            synopsis = "",
            title = "Casshern Sins",
            titleJapanese = "キャシャーン SINS"
        ))
    }

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        getAnimeUseCase = GetAnimeUseCase(repository)
        viewModel = MainViewModel(getAnimeUseCase)
    }

    @Test
    fun `Main repository get anime success`() = runTest {
        `when`(repository.getAnime()).thenReturn(Anime(data = mockData))
        val result = repository.getAnime()
        assert(result.data?.isNotEmpty() == true)
        assertNotNull(result)
    }

    @Test
    fun `Main repository get anime error`() = runTest {
        `when`(repository.getAnime()).thenReturn(null)
        val result = repository.getAnime()
        assertNull(result)
    }

    @Test
    fun `Main view model get anime use case not null`() = runTest {
        `when`(repository.getAnime()).thenReturn(Anime(mockData))
        viewModel.getAmineAgain()
        val value = viewModel.anime.value
        assertNotNull(value?.anime)
        assertThat(value?.isLoading)?.isEqualTo(false)
        assertThat(value?.anime?.data)?.isEqualTo(mockData)
    }
    @Test
    fun `Main view model get anime use case null`() = runTest {
        `when`(repository.getAnime()).thenReturn(null)
        viewModel.getAmineAgain()
        val value = viewModel.anime.value
        assertThat(value?.isLoading)?.isEqualTo(false)
        assertNull(value?.anime)
        assertThat(value?.anime)?.isEqualTo(null)
    }
}