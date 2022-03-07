package com.ordolabs.domain.usecase.color

import com.ordolabs.domain.model.color.ColorRgb
import com.ordolabs.domain.repository.color.ColorValidatorRepository
import com.ordolabs.domain.usecase.BaseUseCase
import kotlinx.coroutines.flow.Flow

interface ValidateColorRgbUseCase : BaseUseCase<ColorRgb?, Boolean>

class ValidateColorRgbUseCaseImpl(
    private val colorValidatorRepository: ColorValidatorRepository
) : ValidateColorRgbUseCase {

    override suspend fun invoke(param: ColorRgb?): Flow<Boolean> =
        colorValidatorRepository.validateColor(param)
}