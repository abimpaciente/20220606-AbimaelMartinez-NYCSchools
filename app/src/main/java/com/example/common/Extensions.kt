package com.example.common

import android.content.Context
import android.widget.Toast
import com.example.model.School
import com.example.model.SchoolSat
import com.example.model.cache.SchoolsDatabase
import com.example.model.cache.entities.SchoolEntity
import com.example.model.cache.entities.SchoolSatEntity
import com.example.model.network.SchoolModel
import com.example.model.network.SchoolSatModel
import com.example.model.repo.Repository

fun Context.toast(message: String, lenght: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, lenght).show()
}

fun SchoolSatModel.toDomain() = SchoolSat(dbn, satTestTakers, readingAvg, mathAvg, writingAvg)
fun SchoolSatEntity.toDomain() = SchoolSat(dbn, satTestTakers, readingAvg, mathAvg, writingAvg)
fun SchoolSatModel.toDatabase() =
    SchoolSatEntity(
        dbn = dbn,
        satTestTakers = satTestTakers,
        readingAvg = readingAvg,
        mathAvg = mathAvg,
        writingAvg = writingAvg
    )

fun SchoolModel.toDomain() = School(dbn, school_name, location, latitude, longitude, schoolEmail)
fun SchoolEntity.toDomain() = School(dbn, school_name, location, latitude, longitude, schoolEmail)
fun SchoolModel.toDatabase() =
    SchoolEntity(
        dbn = dbn,
        school_name = school_name,
        location = location,
        latitude = latitude,
        longitude = longitude,
        schoolEmail = schoolEmail
    )

