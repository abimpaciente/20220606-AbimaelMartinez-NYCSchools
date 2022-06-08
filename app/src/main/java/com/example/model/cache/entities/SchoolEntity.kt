package com.example.model.cache.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "school_table")
data class SchoolEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,
    @ColumnInfo(name = "dbn")
    val dbn: String,
    @ColumnInfo(name = "school_name")
    val school_name: String?,
    @ColumnInfo(name = "location")
    val location: String?,
    @ColumnInfo(name = "latitude")
    val latitude: String?,
    @ColumnInfo(name = "longitude")
    val longitude: String?,
    @ColumnInfo(name = "school_email")
    val schoolEmail: String?
)
