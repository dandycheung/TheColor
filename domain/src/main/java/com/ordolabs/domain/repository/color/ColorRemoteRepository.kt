package com.ordolabs.domain.repository.color

import com.ordolabs.domain.model.color.ColorDetails
import com.ordolabs.domain.model.color.ColorScheme
import com.ordolabs.domain.model.color.ColorSchemeRequest
import kotlinx.coroutines.flow.Flow

interface ColorRemoteRepository {
    suspend fun fetchColorDetails(colorHex: String): Flow<ColorDetails>
    suspend fun fetchColorScheme(request: ColorSchemeRequest): Flow<ColorScheme>
}