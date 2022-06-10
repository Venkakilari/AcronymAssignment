package com.assignment.network.remote

import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val acronymService: AcronymService
) {
    suspend fun getByShortForm(sf: String) = acronymService.getByShortForm(sf)
    suspend fun getByLongForm(lf: String) = acronymService.getByLongForm(lf)
}