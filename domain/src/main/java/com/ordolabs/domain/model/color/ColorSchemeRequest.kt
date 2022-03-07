package com.ordolabs.domain.model.color

data class ColorSchemeRequest(
    val seedHex: String,
    val modeOrdinal: Int,
    val sampleCount: Int
)