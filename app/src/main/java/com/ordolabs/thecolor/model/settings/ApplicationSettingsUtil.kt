package com.ordolabs.thecolor.model.settings

import androidx.appcompat.app.AppCompatDelegate

object ApplicationSettingsUtil {

    val ApplicationSettings.Appearance.Theme.nightMode: Int
        get() = when (this) {
            ApplicationSettings.Appearance.Theme.SYSTEM -> AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
            ApplicationSettings.Appearance.Theme.LIGHT -> AppCompatDelegate.MODE_NIGHT_NO
            ApplicationSettings.Appearance.Theme.DARK -> AppCompatDelegate.MODE_NIGHT_YES
        }
}