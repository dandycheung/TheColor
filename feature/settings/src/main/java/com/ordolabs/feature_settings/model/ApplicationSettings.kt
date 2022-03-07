package com.ordolabs.feature_settings.model

data class ApplicationSettings(
    val appearance: Appearance
) {

    data class Appearance(
        val theme: Theme
    ) {

        enum class Theme {
            SYSTEM, LIGHT, DARK
        }
    }
}