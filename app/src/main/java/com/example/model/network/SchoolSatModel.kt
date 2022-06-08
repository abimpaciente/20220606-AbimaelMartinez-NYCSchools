package com.example.model.network

import com.google.gson.annotations.SerializedName

data class SchoolSatModel(
    val dbn: String,
    @SerializedName("num_of_sat_test_takers")
    val satTestTakers: String,
    @SerializedName("sat_critical_reading_avg_score")
    val readingAvg: String,
    @SerializedName("sat_math_avg_score")
    val mathAvg: String,
    @SerializedName("sat_writing_avg_score")
    val writingAvg: String
)
