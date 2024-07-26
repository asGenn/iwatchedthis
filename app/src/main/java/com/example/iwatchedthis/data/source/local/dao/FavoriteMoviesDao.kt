package com.example.iwatchedthis.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.iwatchedthis.data.source.local.entitiy.FavoriteMovies

@Dao
interface FavoriteMoviesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteMovie(favoriteMovies: FavoriteMovies)

    @Query("SELECT * FROM favorite_movies")
    suspend fun getAllFavoriteMovies(): List<FavoriteMovies>

    @Query("SELECT EXISTS(SELECT * FROM favorite_movies WHERE imdbID = :imdbID)")
    suspend fun isFavorite(imdbID: String): Boolean

    @Query("DELETE FROM favorite_movies WHERE imdbID = :imdbID")
    suspend fun deleteFavoriteMovie(imdbID: String)
}