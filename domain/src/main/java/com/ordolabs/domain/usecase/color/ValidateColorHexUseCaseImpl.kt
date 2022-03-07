package com.ordolabs.domain.usecase.color

import com.ordolabs.domain.model.color.ColorHex
import com.ordolabs.domain.repository.color.ColorValidatorRepository
import com.ordolabs.domain.usecase.BaseUseCase
import kotlinx.coroutines.flow.Flow

interface ValidateColorHexUseCase : BaseUseCase<ColorHex?, Boolean>

class ValidateColorHexUseCaseImpl(
    private val colorValidatorRepository: ColorValidatorRepository
) : ValidateColorHexUseCase {

    override suspend fun invoke(param: ColorHex?): Flow<Boolean> =
        colorValidatorRepository.validateColor(param)
}