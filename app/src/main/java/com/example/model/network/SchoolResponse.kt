package com.example.model

import com.google.gson.annotations.SerializedName

data class SchoolListResponse(
    val dbn: String,
    val school_name: String,
    val location: String,
    val latitude: String,
    val longitude: String,
    @SerializedName("school_email")
    val schoolEmail: String
)

data class SchoolSatResponse(
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
