package com.example.di

import android.content.Context
import androidx.room.Room
import com.example.model.cache.SchoolsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    private const val NameDatabase = "schools_database"

    @Provides
    @Singleton
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context,
            SchoolsDatabase::class.java,
            NameDatabase
        ).build()

    @Provides
    @Singleton
    fun provideSchoolsDao(db: SchoolsDatabase) = db.getSchoolsDao()

}