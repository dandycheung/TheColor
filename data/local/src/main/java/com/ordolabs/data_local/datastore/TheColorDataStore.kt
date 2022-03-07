package com.ordolabs.data_local.datastore

import androidx.datastore.preferences.core.intPreferencesKey

object TheColorDataStore {

    object PreferencesKeys {
        val SETTINGS_APPEARANCE_THEME = intPreferencesKey("settings_appearance_theme")
    }
}