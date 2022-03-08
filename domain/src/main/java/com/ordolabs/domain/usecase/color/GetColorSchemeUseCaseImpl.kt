package com.ordolabs.domain.usecase.color

import com.ordolabs.domain.model.color.ColorScheme
import com.ordolabs.domain.model.color.ColorSchemeRequest
import com.ordolabs.domain.repository.color.ColorRemoteRepository
import com.ordolabs.domain.usecase.BaseUseCase
import kotlinx.coroutines.flow.Flow

interface GetColorSchemeUseCase : BaseUseCase<ColorSchemeRequest, Flow<ColorScheme>>

class GetColorSchemeUseCaseImpl(
    private val colorRemoteRepository: ColorRemoteRepository
) : GetColorSchemeUseCase {

    override suspend fun invoke(param: ColorSchemeRequest): Flow<ColorScheme> =
        colorRemoteRepository.fetchColorScheme(param)
}