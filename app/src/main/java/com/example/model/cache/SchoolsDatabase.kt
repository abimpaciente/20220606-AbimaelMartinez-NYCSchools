package com.example.model.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.model.cache.dao.SchoolsDao
import com.example.model.cache.entities.SchoolEntity
import com.example.model.cache.entities.SchoolSatEntity

@Database(
    entities = [
        SchoolEntity::class,
        SchoolSatEntity::class
    ],
    version = 1
)
abstract class SchoolsDatabase : RoomDatabase() {

    abstract fun getSchoolsDao(): SchoolsDao
}