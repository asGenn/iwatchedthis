package com.example.iwatchedthis.data.source.network.repository

import com.example.iwatchedthis.data.Result
import com.example.iwatchedthis.data.source.network.model.MovieDetail
import com.example.iwatchedthis.data.source.network.model.Movies
import kotlinx.coroutines.flow.Flow


interface MoviesRepository {
    suspend fun getMoviesList(movieName: String): Flow<Result<Movies>>
    suspend fun getMovieDetail(movieId: String): Flow<Result<MovieDetail>>
}