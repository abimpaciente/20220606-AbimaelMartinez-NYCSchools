package com.example.di

import com.example.common.BASE_URL
import com.example.model.network.NycApy
import com.example.model.repo.Repository
import com.example.model.repo.RepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class ServiceModule {
    @Provides
    fun provideApiService(): NycApy =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NycApy::class.java)

    @Provides
    fun provideRepo(service: NycApy): Repository =
        RepositoryImpl(service)

    @Provides
    fun provideDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    fun provideScope(dispatcher: CoroutineDispatcher): CoroutineScope =
        CoroutineScope(dispatcher)
}