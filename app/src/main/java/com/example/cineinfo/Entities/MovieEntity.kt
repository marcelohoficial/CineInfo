package com.example.cineinfo.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey
    val id: Int,
    val title: String,
    val posterPath: String?,
    val backdropPath: String?,
    val year: String,
    val duration: String,
    val rating: Double,
    val synopsis: String,
    val quality: String = "HD",
    val popularity: Double,
    val releaseDate: String,
    val lastUpdated: Long = System.currentTimeMillis()
)

fun MovieEntity.toMovie(posterResId: Int): com.example.cineinfo.data.Movie {
    return com.example.cineinfo.data.Movie(
        id = id,
        title = title,
        posterResId = posterResId,
        year = year.toIntOrNull() ?: 0,
        duration = duration,
        rating = rating,
        synopsis = synopsis,
        quality = quality
    )
}