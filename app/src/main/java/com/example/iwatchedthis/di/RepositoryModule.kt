package com.example.iwatchedthis.di

import com.example.iwatchedthis.data.source.network.repository.TopMoviesRepository
import com.example.iwatchedthis.data.source.network.repository.TopMoviesRepositoryImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindTopMoviesRepo(myTopMoviesRepositoryImp: TopMoviesRepositoryImp):
            TopMoviesRepository


}