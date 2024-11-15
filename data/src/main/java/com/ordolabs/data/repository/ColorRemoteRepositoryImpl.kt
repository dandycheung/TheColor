package com.ordolabs.data.repository

import com.ordolabs.data_remote.api.TheColorApiService
import com.ordolabs.data_remote.mapper.toDomain
import com.ordolabs.domain.model.ColorDetails
import com.ordolabs.domain.model.ColorScheme
import com.ordolabs.domain.model.ColorSchemeRequest
import com.ordolabs.domain.repository.ColorRemoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ColorRemoteRepositoryImpl @Inject constructor(
    private val api: TheColorApiService
) : ColorRemoteRepository {

    override suspend fun fetchColorDetails(colorHex: String): Flow<ColorDetails> = flow {
        val response = api.getColorDetails(hex = colorHex)
        emit(response.toDomain())
    }

    override suspend fun fetchColorScheme(request: ColorSchemeRequest): Flow<ColorScheme> = flow {
        val mode = TheColorApiService.SchemeMode.values()[request.modeOrdinal]
        val response = api.getColorScheme(
            hex = request.seedHex,
            mode = mode,
            sampleCount = request.sampleCount
        )
        emit(response.toDomain())
    }
}