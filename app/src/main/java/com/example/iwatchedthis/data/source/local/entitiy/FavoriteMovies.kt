package com.example.iwatchedthis.data.source.local.entitiy

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(
    tableName = "favorite_movies"
)
data class FavoriteMovies(
    @PrimaryKey(autoGenerate = false)
    val imdbID: String,
    val title: String,
    val year: String,
    val poster: String,
    val type: String

)