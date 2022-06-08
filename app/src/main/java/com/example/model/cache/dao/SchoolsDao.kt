package com.example.model.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.model.cache.entities.SchoolEntity
import com.example.model.cache.entities.SchoolSatEntity

@Dao
interface SchoolsDao {

    @Query("SELECT * FROM school_table order by dbn")
    suspend fun getSchools(): List<SchoolEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSchools(schools: List<SchoolEntity>)

    @Query("SELECT * FROM school_sat_table")
    suspend fun getSchoolsSat(): List<SchoolSatEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSchoolsSat(schools: List<SchoolSatEntity>)

    @Query("DELETE FROM school_table")
    suspend fun deleteAllSchool()

    @Query("DELETE FROM school_sat_table")
    suspend fun deleteAllSchoolSat()
}