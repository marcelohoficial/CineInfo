package com.example.cineinfo.data.repository

import com.example.cineinfo.data.local.MovieDao
import com.example.cineinfo.data.local.MovieEntity
import com.example.cineinfo.data.remote.TmdbApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.coroutines.flow.Flow

class MovieRepository(
    private val api: TmdbApiService,
    private val dao: MovieDao,
    private val apiKey: String
) {
    val moviesFlow: Flow<List<MovieEntity>> = dao.getAll()

    suspend fun refreshFromNetwork() {
        if (apiKey.isBlank()) {
            throw Exception("API Key is missing. Check local.properties.")
        }
        withContext(Dispatchers.IO) {
            val resp = api.getPopular(apiKey)
            val entities = resp.results.map {
                MovieEntity(
                    id = it.id,
                    title = it.title,
                    overview = it.overview,
                    voteAverage = it.vote_average,
                    posterPath = it.poster_path,
                    releaseDate = it.release_date
                )
            }
            dao.insertAll(entities)
        }
    }

    suspend fun searchMovies(query: String): List<MovieEntity> {
        if (apiKey.isBlank()) {
            throw Exception("API Key is missing. Check local.properties.")
        }
        return withContext(Dispatchers.IO) {
            val resp = api.searchMovie(apiKey, query)
            resp.results.map {
                MovieEntity(
                    id = it.id,
                    title = it.title,
                    overview = it.overview,
                    voteAverage = it.vote_average,
                    posterPath = it.poster_path,
                    releaseDate = it.release_date
                )
            }
        }
    }
}