package com.assignment.network.remote

import com.assignment.data.model.networkmodel.AcronymResponse
import com.assignment.network.model.BaseApiResponse
import com.assignment.network.utils.NetworkResult
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@ActivityRetainedScoped
class Repository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : BaseApiResponse() {
    suspend fun getByShortForm(sf: String): Flow<NetworkResult<List<AcronymResponse>>> {
        return flow {
            emit(safeApiCall { remoteDataSource.getByShortForm(sf) })
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getByLongForm(lf: String): Flow<NetworkResult<List<AcronymResponse>>> {
        return flow {
            emit(safeApiCall { remoteDataSource.getByLongForm(lf) })
        }.flowOn(Dispatchers.IO)
    }
}