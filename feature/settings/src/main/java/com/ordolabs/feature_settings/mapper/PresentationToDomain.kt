package com.ordolabs.feature_settings.mapper

import com.ordolabs.feature_settings.model.ApplicationSettings
import com.ordolabs.domain.model.settings.ApplicationSettings as ApplicationSettingsDomain

fun ApplicationSettings.toDomain() =
    ApplicationSettingsDomain(
        appearance = this.appearance.toDomain()
    )

fun ApplicationSettings.Appearance.toDomain() =
    ApplicationSettingsDomain.Appearance(
        themeOrdinal = this.theme.ordinal
    )
