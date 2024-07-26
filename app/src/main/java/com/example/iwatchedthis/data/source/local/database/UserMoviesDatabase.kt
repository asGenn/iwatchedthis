package com.example.iwatchedthis.data.source.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.iwatchedthis.data.source.local.dao.FavoriteMoviesDao
import com.example.iwatchedthis.data.source.local.dao.MoviesCommentDao
import com.example.iwatchedthis.data.source.local.dao.UserProfileDao
import com.example.iwatchedthis.data.source.local.dao.WatchedMoviesDao
import com.example.iwatchedthis.data.source.local.entitiy.FavoriteMovies
import com.example.iwatchedthis.data.source.local.entitiy.MoviesComment
import com.example.iwatchedthis.data.source.local.entitiy.UserProfile
import com.example.iwatchedthis.data.source.local.entitiy.WatchedMovies


@Database(
    entities = [MoviesComment::class, FavoriteMovies::class, WatchedMovies::class, UserProfile::class],
    version = 1,

    exportSchema = true
)
abstract class UserMoviesDatabase : RoomDatabase() {
    abstract fun moviesCommentDao(): MoviesCommentDao
    abstract fun favoriteMoviesDao(): FavoriteMoviesDao
    abstract fun watchedMoviesDao(): WatchedMoviesDao
    abstract fun userProfileDao(): UserProfileDao

}