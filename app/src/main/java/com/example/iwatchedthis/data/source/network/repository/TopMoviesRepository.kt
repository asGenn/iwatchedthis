package com.example.iwatchedthis.data.source.network.repository

import com.example.iwatchedthis.data.Result

import com.example.iwatchedthis.data.source.network.model.TopMoviesItem
import kotlinx.coroutines.flow.Flow

interface TopMoviesRepository {
    suspend fun getTopMoviesList(): Flow<Result<List<TopMoviesItem>>>
}