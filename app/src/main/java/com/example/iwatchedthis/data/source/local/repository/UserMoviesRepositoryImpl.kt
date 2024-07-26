package com.example.iwatchedthis.data.source.local.repository

import com.example.iwatchedthis.data.source.local.dao.FavoriteMoviesDao
import com.example.iwatchedthis.data.source.local.dao.MoviesCommentDao
import com.example.iwatchedthis.data.source.local.dao.UserProfileDao
import com.example.iwatchedthis.data.source.local.dao.WatchedMoviesDao
import com.example.iwatchedthis.data.source.local.entitiy.FavoriteMovies
import com.example.iwatchedthis.data.source.local.entitiy.MoviesComment
import com.example.iwatchedthis.data.source.local.entitiy.UserProfile
import com.example.iwatchedthis.data.source.local.entitiy.WatchedMovies
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UserMoviesRepositoryImpl(
    private val commentDao: MoviesCommentDao,
    private val watchedMoviesDao: WatchedMoviesDao,
    private val favoriteMoviesDao: FavoriteMoviesDao,
    private val userProfileDao: UserProfileDao
) : UserMoviesRepository {
    override suspend fun insertComment(imdbID: String, comment: String, movieName: String) {
        commentDao.insertComment(
            MoviesComment(
                imdbID = imdbID,
                comment = comment,
                movieName = movieName
            )
        )
    }

    override suspend fun getAllComment(): Flow<List<MoviesComment>> {
        return flow {
            emit(commentDao.getAllComment())
        }

    }

    override suspend fun insertFavoriteMovie(movies: FavoriteMovies) {
        favoriteMoviesDao.insertFavoriteMovie(
            movies
        )
    }

    override suspend fun getAllFavoriteMovies(): Flow<List<FavoriteMovies>> {
        return flow {
            emit(favoriteMoviesDao.getAllFavoriteMovies())
        }
    }

    override suspend fun isFavorite(imdbID: String): Boolean {
        return favoriteMoviesDao.isFavorite(imdbID)
    }

    override suspend fun deleteFavoriteMovie(imdbID: String) {
        favoriteMoviesDao.deleteFavoriteMovie(imdbID)
    }

    override suspend fun insertWatchedMovie(watchedMovies: WatchedMovies) {
        watchedMoviesDao.insertWatchedMovie(
            watchedMovies
        )

    }

    override suspend fun getAllWatchedMovies(): Flow<List<WatchedMovies>> {
        return flow {
            emit(watchedMoviesDao.getAllWatchedMovies())
        }
    }

    override suspend fun isWatched(imdbID: String): Boolean {
        return watchedMoviesDao.isWatched(imdbID)
    }

    override suspend fun deleteWatchedMovie(imdbID: String) {
        watchedMoviesDao.deleteWatchedMovie(imdbID)
    }

    override suspend fun getUserName(): String? {
        return userProfileDao.getUserName()
    }

    override suspend fun updateUserName(userProfile: UserProfile) {
        userProfileDao.insertUserName(userProfile)
    }

}