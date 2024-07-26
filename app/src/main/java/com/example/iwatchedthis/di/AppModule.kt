package com.example.iwatchedthis.di

import com.example.iwatchedthis.data.source.network.api.CollectApi
import com.example.iwatchedthis.data.source.network.api.RapidApi
import com.example.iwatchedthis.data.source.network.repository.MoviesRepository
import com.example.iwatchedthis.data.source.network.repository.MoviesRepositoryImp
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideCollectApi(): CollectApi {
        return Retrofit.Builder()
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .baseUrl(CollectApi.BASE_URL)
            .client(OkHttpClient.Builder().build())
            .build()
            .create(CollectApi::class.java)
        // Provide your api here
    }

    @Provides
    @Singleton
    fun provideRapidApi(): RapidApi {
        return Retrofit.Builder()
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .baseUrl(RapidApi.BASE_URL)
            .client(OkHttpClient.Builder().build())
            .build()
            .create(RapidApi::class.java)
        // Provide your api here
    }

    @Provides
    @Singleton
    fun provideMoviesRepository(api: CollectApi): MoviesRepository {
        return MoviesRepositoryImp(api)
    }


}