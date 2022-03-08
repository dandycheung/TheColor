package com.ordolabs.domain.model.settings

data class ApplicationSettings(
    val appearance: Appearance
) {

    sealed interface Category

    data class Appearance(
        val themeOrdinal: Int
    ) : Category
}