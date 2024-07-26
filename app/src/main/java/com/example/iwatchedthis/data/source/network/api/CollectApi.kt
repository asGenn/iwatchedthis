package com.example.iwatchedthis.data.source.network.api

import com.example.iwatchedthis.data.source.network.model.MovieDetail
import com.example.iwatchedthis.data.source.network.model.Movies
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface CollectApi {
    @Headers(
        "content-type: application/json",
        "authorization: apikey 5nrPEktwaBrs8DrFMUB9ks:2Fvni2JXLbr29zlDHBO0Pq"
    )
    @GET("imdb/imdbSearchByName")
    suspend fun getMoviesList(

        @Query("query") query: String
    ): Movies

    @GET("imdb/imdbSearchById")
    @Headers(
        "content-type: application/json",
        "authorization: apikey 5nrPEktwaBrs8DrFMUB9ks:2Fvni2JXLbr29zlDHBO0Pq"
    )
    suspend fun getMovieDetail(
        @Query("movieId") movieId: String
    ): MovieDetail


    companion object {
        const val BASE_URL = "https://api.collectapi.com/"
    }
}