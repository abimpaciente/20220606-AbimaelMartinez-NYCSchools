package com.example.model.repo

import com.example.model.StateUI
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
) : Repository {
    override suspend fun getSchools() = flow {
        emit(StateUI.Loading)
        try {
            val response = service.getSchoolList()
            if (response.isSuccessful) {
                response.body()?.let { result ->
                    emit(StateUI.Success(result))
                } ?: throw Exception("Response null")
            } else {
                throw Exception("Network call failed")
            }
        } catch (e: Exception) {
            emit(StateUI.Error(e))
        }
    }

    override suspend fun getSchoolsSat(dbn: String): Flow<StateUI> =
        flow {
            try {
                val response = service.getSchoolSat(dbn)
                if (response.isSuccessful) {
                    response.body()?.let {
                        emit(StateUI.Success(it))
                    } ?: throw Exception("Response null")
                } else throw Exception("Network call failed")
            } catch (e: Exception) {
                emit(StateUI.Error(e))
            }
        }


}