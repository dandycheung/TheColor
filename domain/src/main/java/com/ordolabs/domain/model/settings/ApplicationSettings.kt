package com.ordolabs.domain.model.settings

data class ApplicationSettings(
    val appearance: Appearance
) {

    data class Appearance(
        val themeOrdinal: Int
    )
}