package com.example.iwatchedthis.data.source.network.repository

import android.app.Application
import com.example.iwatchedthis.R
import com.example.iwatchedthis.data.Result
import com.example.iwatchedthis.data.source.network.model.TopMoviesItem
import com.example.iwatchedthis.data.source.network.api.RapidApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class TopMoviesRepositoryImp @Inject constructor(
    private val rapidApi: RapidApi,
    private val context: Application
) : TopMoviesRepository {
    override suspend fun getTopMoviesList(): Flow<Result<List<TopMoviesItem>>> {

        return flow {
            val topMoviesFromApi = try {
                rapidApi.getTopMoviesList(
                    context.getString(R.string.rapidApiKey),
                    context.getString(R.string.rapidHost)
                )

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
            emit(Result.Success(data = topMoviesFromApi))
        }

    }


}