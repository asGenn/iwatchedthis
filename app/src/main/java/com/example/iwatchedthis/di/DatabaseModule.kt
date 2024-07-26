package com.example.iwatchedthis.di

import android.app.Application
import androidx.room.Room
import com.example.iwatchedthis.data.source.local.dao.FavoriteMoviesDao
import com.example.iwatchedthis.data.source.local.dao.MoviesCommentDao
import com.example.iwatchedthis.data.source.local.dao.UserProfileDao
import com.example.iwatchedthis.data.source.local.dao.WatchedMoviesDao
import com.example.iwatchedthis.data.source.local.database.UserMoviesDatabase
import com.example.iwatchedthis.data.source.local.repository.UserMoviesRepository
import com.example.iwatchedthis.data.source.local.repository.UserMoviesRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDataBase(context: Application): UserMoviesDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            UserMoviesDatabase::class.java,
            "user_movies.db"
        )
            .build()
    }

    @Provides
    @Singleton
    fun provideMovieCommentDao(database: UserMoviesDatabase): MoviesCommentDao =
        database.moviesCommentDao()

    @Provides
    @Singleton
    fun provideFavoriteMoviesDao(database: UserMoviesDatabase): FavoriteMoviesDao =
        database.favoriteMoviesDao()

    @Provides
    @Singleton
    fun provideWatchedMoviesDao(database: UserMoviesDatabase): WatchedMoviesDao =
        database.watchedMoviesDao()

    @Provides
    @Singleton
    fun provideUserProfileDao(database: UserMoviesDatabase): UserProfileDao =
        database.userProfileDao()


    @Provides
    @Singleton
    fun provideUserMoviesRepository(
        commentDao: MoviesCommentDao,
        watchedMoviesDao: WatchedMoviesDao,
        favoriteMoviesDao: FavoriteMoviesDao,
        userProfileDao: UserProfileDao
    ): UserMoviesRepository {
        return UserMoviesRepositoryImpl(
            commentDao,
            watchedMoviesDao,
            favoriteMoviesDao,
            userProfileDao
        )
    }
}