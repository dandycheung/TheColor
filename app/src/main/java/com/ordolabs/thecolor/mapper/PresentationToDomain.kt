package com.ordolabs.thecolor.mapper

import com.ordolabs.domain.model.color.ColorHex
import com.ordolabs.domain.model.color.ColorRgb
import com.ordolabs.thecolor.model.color.Color
import com.ordolabs.thecolor.model.color.ColorPrototype
import com.ordolabs.thecolor.model.color.data.ColorSchemeRequest
import com.ordolabs.thecolor.model.color.from
import com.ordolabs.thecolor.model.color.toHex
import com.ordolabs.thecolor.model.settings.ApplicationSettings
import com.ordolabs.domain.model.color.ColorSchemeRequest as ColorSchemeRequestDomain
import com.ordolabs.domain.model.settings.ApplicationSettings as ApplicationSettingsDomain

// region ColorPrototype

fun ColorPrototype.Hex.toDomain(): ColorHex? {
    Color.from(this) ?: return null
    return ColorHex(
        value = this.value!! // checked in converting to valid color above
    )
}

fun ColorPrototype.Rgb.toDomain(): ColorRgb? {
    Color.from(this) ?: return null
    return ColorRgb(
        r = this.r!!, // checked in converting to valid color above
        g = this.g!!, // checked in converting to valid color above
        b = this.b!! // checked in converting to valid color above
    )
}

// endregion

// region ColorSchemeRequest

fun ColorSchemeRequest.toDomain(): ColorSchemeRequestDomain {
    return ColorSchemeRequestDomain(
        seedHex = this.seed.toHex().value!!,
        modeOrdinal = this.config.modeOrdinal,
        sampleCount = this.config.sampleCount
    )
}

// endregion

// region ApplicationSettings

fun ApplicationSettings.toDomain() =
    ApplicationSettingsDomain(
        appearance = this.appearance.toDomain()
    )

fun ApplicationSettings.Appearance.toDomain() =
    ApplicationSettingsDomain.Appearance(
        themeOrdinal = this.theme.ordinal
    )

// endregion