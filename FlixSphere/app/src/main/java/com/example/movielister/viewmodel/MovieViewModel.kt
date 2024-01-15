package com.example.movielister.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movielister.model.NowPlaying
import com.example.movielister.model.PopularMovie
import com.example.movielister.model.TopRated
import com.example.movielister.components.network.API_KEY
import com.example.movielister.components.network.NowPlayingApi
import com.example.movielister.components.network.PopularMovieApi
import com.example.movielister.components.network.TopRatedMovieApi
import kotlinx.coroutines.launch
import java.io.IOException



sealed interface PopularMovie_UiState {
    data class Success(val PopularMovie: PopularMovie) : PopularMovie_UiState
    //    data class Success(val photos: List<MarsPhoto>) : MarsUiState
    object Error : PopularMovie_UiState
    object Loading : PopularMovie_UiState
}

sealed interface TopRatedMovie_UiState {
    data class Success(val TopRatedMovie: TopRated) : TopRatedMovie_UiState
    //    data class Success(val photos: List<MarsPhoto>) : MarsUiState
    object Error : TopRatedMovie_UiState
    object Loading : TopRatedMovie_UiState
}

sealed interface NowPlaying_UiState {
    data class Success(val NowPlayingMovie: NowPlaying) : NowPlaying_UiState
    //    data class Success(val photos: List<MarsPhoto>) : MarsUiState
    object Error : NowPlaying_UiState
    object Loading : NowPlaying_UiState
}

class MovieViewModel : ViewModel() {

//    private val repository = MovieRepository(movieService = MovieApi.create())

    var popularmovie_UiState: PopularMovie_UiState by mutableStateOf(PopularMovie_UiState.Loading)
        private set

    var topratedmovie_UiState: TopRatedMovie_UiState by mutableStateOf(TopRatedMovie_UiState.Loading)
        private set

    var nowplayingmovie_UiState: NowPlaying_UiState by mutableStateOf(NowPlaying_UiState.Loading)
        private set

    init {
        getPopularMovies()
        getTopRatedMovies()
        getNowPlayingMovies()
    }

    private fun getNowPlayingMovies() {
        viewModelScope.launch {
            nowplayingmovie_UiState= NowPlaying_UiState.Loading
            nowplayingmovie_UiState=try {

                val NowplayingResult= NowPlayingApi.retrofitService.getNowPlayingMovies(API_KEY)
                NowPlaying_UiState.Success(NowplayingResult)
            } catch (e: IOException) {
                NowPlaying_UiState.Error
            }

        }
    }

    private fun getTopRatedMovies() {
        viewModelScope.launch {
            topratedmovie_UiState= TopRatedMovie_UiState.Loading
            topratedmovie_UiState=try {

                val TopRatedResult= TopRatedMovieApi.retrofitService.getTopRatedMovies(API_KEY)
                TopRatedMovie_UiState.Success(TopRatedResult)
            } catch (e: IOException) {
                TopRatedMovie_UiState.Error
            }

        }
    }

    private fun getPopularMovies() {
        viewModelScope.launch {
            popularmovie_UiState= PopularMovie_UiState.Loading
            popularmovie_UiState=try {

                val PopularResult= PopularMovieApi.retrofitService.getPopularMovies(API_KEY)
                PopularMovie_UiState.Success(PopularResult)
            } catch (e: IOException) {
                PopularMovie_UiState.Error
            }

        }
    }


}

