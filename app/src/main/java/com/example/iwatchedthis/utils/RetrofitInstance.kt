package com.example.iwatchedthis.utils

import com.example.iwatchedthis.data.source.network.api.CollectApi
import com.example.iwatchedthis.data.source.network.api.RapidApi
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit

object RetrofitInstance {

    private val client: OkHttpClient = OkHttpClient.Builder().build()
    val collectApi: CollectApi by lazy {
        Retrofit.Builder()
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .baseUrl(CollectApi.BASE_URL)

            .client(client)

            .build()
            .create(CollectApi::class.java)
    }
    val rapidApi: RapidApi by lazy {
        Retrofit.Builder()
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .baseUrl(RapidApi.BASE_URL)
            .client(client)
            .build()
            .create(RapidApi::class.java)
    }


}