package com.example.model

import com.example.model.cache.entities.SchoolSatEntity
import com.example.model.network.SchoolSatModel

data class SchoolSat(
    val dbn: String,
    val satTestTakers: String,
    val readingAvg: String,
    val mathAvg: String,
    val writingAvg: String
)

