package com.example.iwatchedthis.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.iwatchedthis.data.source.local.entitiy.WatchedMovies


@Dao
interface WatchedMoviesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWatchedMovie(watchedMovies: WatchedMovies)

    @Query("SELECT * FROM watched_movies")
    suspend fun getAllWatchedMovies(): List<WatchedMovies>

    @Query("SELECT EXISTS(SELECT * FROM watched_movies WHERE imdbID = :imdbID)")
    suspend fun isWatched(imdbID: String): Boolean

    @Query("DELETE FROM watched_movies WHERE imdbID = :imdbID")
    suspend fun deleteWatchedMovie(imdbID: String)

}