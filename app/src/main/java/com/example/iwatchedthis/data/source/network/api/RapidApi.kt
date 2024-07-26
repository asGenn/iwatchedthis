package com.example.iwatchedthis.data.source.network.api


import com.example.iwatchedthis.data.source.network.model.TopMoviesItem
import retrofit2.http.GET
import retrofit2.http.Header


interface RapidApi {


    @GET("/")
    suspend fun getTopMoviesList(
        @Header("x-rapidapi-key") apiKey: String,
        @Header("x-rapidapi-host") host: String
    ): List<TopMoviesItem>

    companion object {
        const val BASE_URL = "https://imdb-top-100-movies.p.rapidapi.com/"
    }
}