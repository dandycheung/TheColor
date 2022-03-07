package com.ordolabs.data_local.mapper

import androidx.datastore.preferences.core.Preferences
import com.ordolabs.data_local.datastore.TheColorDataStore
import com.ordolabs.domain.model.settings.ApplicationSettings

// region Settings

fun Preferences.toApplicationSettings(): ApplicationSettings =
    ApplicationSettings(
        appearance = this.toApplicationAppearanceSettings()
    )

fun Preferences.toApplicationAppearanceSettings(): ApplicationSettings.Appearance =
    ApplicationSettings.Appearance(
        themeOrdinal = this[TheColorDataStore.PreferencesKeys.SETTINGS_APPEARANCE_THEME] ?: 0
    )

// endregion