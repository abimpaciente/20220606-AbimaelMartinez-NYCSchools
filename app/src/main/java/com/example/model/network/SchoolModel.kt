package com.example.model.network

import com.google.gson.annotations.SerializedName

data class SchoolModel(
    val dbn: String,
    val school_name: String,
    val location: String,
    val latitude: String,
    val longitude: String,
    @SerializedName("school_email")
    val schoolEmail: String
)
