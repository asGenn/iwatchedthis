package com.example.iwatchedthis.data.source.local.repository

import com.example.iwatchedthis.data.source.local.entitiy.FavoriteMovies
import com.example.iwatchedthis.data.source.local.entitiy.MoviesComment
import com.example.iwatchedthis.data.source.local.entitiy.UserProfile
import com.example.iwatchedthis.data.source.local.entitiy.WatchedMovies
import kotlinx.coroutines.flow.Flow

interface UserMoviesRepository {

    // insert comment to database
    suspend fun insertComment(imdbID: String, comment: String, movieName: String)

    // get all comments from database
    suspend fun getAllComment(): Flow<List<MoviesComment>>


    // Favorite movie

    // insert favorite movie to database
    suspend fun insertFavoriteMovie(movies: FavoriteMovies)

    // get all favorite movies from database
    suspend fun getAllFavoriteMovies(): Flow<List<FavoriteMovies>>

    //check if movie is favorite
    suspend fun isFavorite(imdbID: String): Boolean

    // delete favorite movie from database
    suspend fun deleteFavoriteMovie(imdbID: String)


    //watched movie

    // insert watched movie to database
    suspend fun insertWatchedMovie(watchedMovies: WatchedMovies)

    // get all watched movies from database
    suspend fun getAllWatchedMovies(): Flow<List<WatchedMovies>>

    //check if movie is watched
    suspend fun isWatched(imdbID: String): Boolean

    // delete watched movie from database
    suspend fun deleteWatchedMovie(imdbID: String)

    // user profile settings
    suspend fun getUserName(): String?

    // update user name
    suspend fun updateUserName(userProfile: UserProfile)

}