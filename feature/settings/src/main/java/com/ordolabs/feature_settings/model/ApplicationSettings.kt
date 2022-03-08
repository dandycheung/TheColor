package com.ordolabs.feature_settings.model

data class ApplicationSettings(
    val appearance: Appearance
) {

    sealed interface Category

    data class Appearance(
        val theme: Theme
    ) : Category {

        enum class Theme {
            SYSTEM, LIGHT, DARK
        }
    }
}