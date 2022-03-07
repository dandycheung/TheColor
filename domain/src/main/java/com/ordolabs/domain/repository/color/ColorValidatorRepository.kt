package com.ordolabs.domain.repository.color

import com.ordolabs.domain.model.color.ColorHex
import com.ordolabs.domain.model.color.ColorRgb
import kotlinx.coroutines.flow.Flow

interface ColorValidatorRepository {

    fun validateColor(color: ColorHex?): Flow<Boolean>
    fun validateColor(color: ColorRgb?): Flow<Boolean>
}