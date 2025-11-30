package com.example.cineinfo.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

data class TmdbMovieDto(
    val id: Int,
    val title: String,
    val overview: String,
    val vote_average: Double,
    val poster_path: String?
)

data class PopularResponse(val results: List<TmdbMovieDto>)

interface TmdbApiService {
    @GET("movie/popular")
    suspend fun getPopular(
        @Query("api_key") apiKey: String,
        @Query("language") lang: String = "pt-BR",
        @Query("page") page: Int = 1
    ): PopularResponse

    @GET("search/movie")
    suspend fun searchMovie(
        @Query("api_key") apiKey: String,
        @Query("query") query: String,
        @Query("language") lang: String = "pt-BR",
        @Query("page") page: Int = 1
    ): PopularResponse
}