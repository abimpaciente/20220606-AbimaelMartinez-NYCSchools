package com.example.model.repo

import android.content.ContentValues.TAG
import android.util.Log
import com.example.common.InternetCheck
import com.example.common.toDatabase
import com.example.common.toDomain
import com.example.model.StateUI
import com.example.model.cache.dao.SchoolsDao
import com.example.model.network.NycApy
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface Repository {
    suspend fun getSchools(): Flow<StateUI>
    suspend fun getSchoolsSat(dbn: String): Flow<StateUI>
}

class RepositoryImpl @Inject constructor(
    private val service: NycApy,
    private val dao: SchoolsDao,
) : Repository {
    override suspend fun getSchools() = flow {
        emit(StateUI.Loading)
        try {
            val connected = InternetCheck()
            if (connected.isConnected()) {
                emit(StateUI.Message("Loading from network"))
                val response = service.getSchoolList()
                if (response.isSuccessful) {
                    response.body()?.let { result ->
                        dao.deleteAllSchool()
                        dao.insertSchools(result.map { it.toDatabase() })
                        emit(StateUI.Success(result.map { it.toDomain() }))
                    } ?: throw Exception("Response null")
                } else {
                    throw Exception("Network call failed")
                }
            } else {
                emit(StateUI.Message("Loading from cache"))
                val cache = dao.getSchools()
                if (!cache.isNullOrEmpty()) {
                    emit(StateUI.Success(cache.map { it.toDomain() }))
                } else {
                    throw Exception("Cache failed")
                }
            }
        } catch (e: Exception) {
            Log.d(TAG, "getSchools: ${e.message}")
            emit(StateUI.Error(e))
        }
    }

    override suspend fun getSchoolsSat(dbn: String) = flow {
        emit(StateUI.Loading)
        try {
            val connected = InternetCheck()
            if (connected.isConnected()) {
                emit(StateUI.Message("Loading from network"))
                val response = service.getSchoolSat()
                if (response.isSuccessful) {
                    response.body()?.let { result ->
                        if (result.isNotEmpty()) {
                            dao.deleteAllSchoolSat()
                            dao.insertSchoolsSat(result.map { it.toDatabase() })
                            result
                        } else {
                            dao.getSchoolsSat()
                        }
                        emit(StateUI.Success(result.map {
                            it.toDomain()
                        }.filter { x ->
                            x.dbn == dbn
                        }))
                    } ?: throw Exception("Response null")
                }
            } else {
                emit(StateUI.Message("Loading from cache"))
                val cache = dao.getSchoolsSat()
                if (!cache.isNullOrEmpty()) {
                    emit(StateUI.Success(cache.map {
                        it.toDomain()
                    }.filter { x ->
                        x.dbn == dbn
                    }))
                } else {
                    throw Exception("Network call failed")
                }
            }
        } catch (e: Exception) {
            emit(StateUI.Error(e))
        }
    }


}