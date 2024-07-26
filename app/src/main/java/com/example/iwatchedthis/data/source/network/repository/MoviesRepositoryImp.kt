package com.example.iwatchedthis.data.source.network.repository


import com.example.iwatchedthis.data.Result
import com.example.iwatchedthis.data.source.network.model.MovieDetail
import com.example.iwatchedthis.data.source.network.model.Movies
import com.example.iwatchedthis.data.source.network.api.CollectApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException


class MoviesRepositoryImp(
    private val api: CollectApi
) : MoviesRepository {
    override suspend fun getMoviesList(movieName: String): Flow<Result<Movies>> {
        return flow {
            val moviesFromApi = try {
                api.getMoviesList(movieName)

            } catch (e: IOException) {
                e.printStackTrace()
                emit(Result.Error(message = "Hata meydana geldi"))
                return@flow
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Result.Error(message = "Hata meydana geldi"))
                return@flow
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Result.Error(message = "Hata meydana geldi"))
                return@flow

            }
            emit(Result.Success(moviesFromApi))

        }

    }

    override suspend fun getMovieDetail(movieId: String): Flow<Result<MovieDetail>> {
        return flow {
            val movieDetailFromApi = try {
                api.getMovieDetail(movieId)

            } catch (e: IOException) {
                e.printStackTrace()
                emit(Result.Error(message = "Hata meydana geldi"))
                return@flow
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Result.Error(message = "Hata meydana geldi"))
                return@flow
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Result.Error(message = "Hata meydana geldi"))
                return@flow

            }
            emit(Result.Success(movieDetailFromApi))

        }
    }
}