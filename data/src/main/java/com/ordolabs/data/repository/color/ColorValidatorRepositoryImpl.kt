package com.ordolabs.data.repository.color

import com.ordolabs.domain.model.color.ColorHex
import com.ordolabs.domain.model.color.ColorRgb
import com.ordolabs.domain.repository.color.ColorValidatorRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ColorValidatorRepositoryImpl : ColorValidatorRepository {

    override fun validateColor(color: ColorHex?): Flow<Boolean> = flow {
        if (color == null) {
            emit(false)
        } else {
            val value = color.value
            val valid = Regex(HEX_COLOR_VALIDATION_REGEX_PATTERN).matches(value)
            emit(valid)
        }
    }

    override fun validateColor(color: ColorRgb?): Flow<Boolean> = flow {
        if (color == null) {
            emit(false)
        } else {
            val componentRange = 0..255
            emit(color.r in componentRange && color.g in componentRange && color.b in componentRange)
        }
    }

    companion object {

        private const val HEX_COLOR_VALIDATION_REGEX_PATTERN = "^([a-fA-F0-9]{6}|[a-fA-F0-9]{3})\$"
    }
}