package com.example.model.network

import com.example.common.END_POINT_SAT
import com.example.common.END_POINT_SCHOOLS
import com.example.model.SchoolListResponse
import com.example.model.SchoolSatResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NycApy {
    @GET(END_POINT_SCHOOLS)
    suspend fun getSchoolList(): Response<List<SchoolListResponse>>

    @GET(END_POINT_SAT)
    suspend fun getSchoolSat(
        @Query("dbn") dbn: String
    ): Response<List<SchoolSatResponse>>
}