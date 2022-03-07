package com.ordolabs.feature_settings.mapper

import com.ordolabs.feature_settings.model.ApplicationSettings
import com.ordolabs.thecolor.util.ext.getFromEnum
import com.ordolabs.domain.model.settings.ApplicationSettings as ApplicationSettingsDomain

fun ApplicationSettingsDomain.toPresentation() =
    ApplicationSettings(
        appearance = this.appearance.toPresentation()
    )

fun ApplicationSettingsDomain.Appearance.toPresentation() =
    ApplicationSettings.Appearance(
        theme = getFromEnum(this.themeOrdinal)
    )