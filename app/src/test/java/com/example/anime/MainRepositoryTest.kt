package com.example.anime

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.anime.domain.model.Anime
import com.example.anime.domain.model.AnimeData
import com.example.anime.domain.repository.MainRepository
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

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainDispatcherRule = MainDispatcherRule()

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun `Main view model get anime success`() = runTest {
        val mockData: MutableList<AnimeData> = mutableListOf<AnimeData>().apply {
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
        `when`(repository.getAnime()).thenReturn(Anime(data = mockData))
        val result = repository.getAnime()
        println("#### result not null: $result")
        assert(result.data?.isNotEmpty() == true)
        assertNotNull(result)
    }

    @Test
    fun `Main view model get anime error`() = runTest {
        `when`(repository.getAnime()).thenReturn(null)
        val result = repository.getAnime()
        println("#### result null: $result")
        assertNull(result)
    }
}