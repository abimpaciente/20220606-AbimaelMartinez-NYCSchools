package com.example.di

import com.example.common.InternetCheck
import com.example.model.cache.dao.SchoolsDao
import com.example.model.network.NycApy
import com.example.model.repo.Repository
import com.example.model.repo.RepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    fun provideRepo(service: NycApy, dao: SchoolsDao):
            Repository = RepositoryImpl(service, dao)
}