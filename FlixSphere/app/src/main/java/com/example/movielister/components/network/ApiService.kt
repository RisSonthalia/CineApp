package com.example.movielister.components.network



import com.example.movielister.model.NowPlaying
import com.example.movielister.model.PopularMovie
import com.example.movielister.model.TopRated
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.GET

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Query




private val retrofit =Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

//interface ApiService{
//    @GET("movie/popular?api_key=$API_KEY")
//    fun getPopularMovies(): Call<PopularMovie>
//}

interface PopularMovie_ApiService {
    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("api_key") apiKey: String): PopularMovie
}


object PopularMovieApi{
    val retrofitService : PopularMovie_ApiService by lazy {
        retrofit.create(PopularMovie_ApiService ::class.java)
    }
}

interface TopRatedMovie_ApiService {
    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(@Query("api_key") apiKey: String):TopRated
}


object TopRatedMovieApi{
    val retrofitService : TopRatedMovie_ApiService by lazy {
        retrofit.create(TopRatedMovie_ApiService ::class.java)
    }
}

interface NowPlaying_ApiService {
    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(@Query("api_key") apiKey: String): NowPlaying
}


object NowPlayingApi{
    val retrofitService : NowPlaying_ApiService by lazy {
        retrofit.create(NowPlaying_ApiService ::class.java)
    }
}

