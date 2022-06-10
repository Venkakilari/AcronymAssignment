package com.assignment.network.remote

import com.assignment.data.model.networkmodel.AcronymResponse
import com.assignment.network.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AcronymService {
    @GET(Constants.DICTIONARY)
    suspend fun getByShortForm(
        @Query("sf") sf: String
    ): Response<List<AcronymResponse>>

    @GET(Constants.DICTIONARY)
    suspend fun getByLongForm(
        @Query("lf") lf: String
    ): Response<List<AcronymResponse>>
}
