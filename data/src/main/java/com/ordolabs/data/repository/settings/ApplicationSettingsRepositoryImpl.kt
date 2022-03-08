package com.ordolabs.data.repository.settings

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.ordolabs.data_local.datastore.TheColorDataStore
import com.ordolabs.data_local.mapper.toApplicationSettings
import com.ordolabs.domain.model.settings.ApplicationSettings
import com.ordolabs.domain.repository.settings.ApplicationSettingsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ApplicationSettingsRepositoryImpl(
    private val datastore: DataStore<Preferences>
) : ApplicationSettingsRepository {

    override fun getApplicationSettings(): Flow<ApplicationSettings> =
        datastore.data.map { prefs ->
            prefs.toApplicationSettings()
        }

    override suspend fun editApplicationSettings(category: ApplicationSettings.Category) =
        when (category) {
            is ApplicationSettings.Appearance -> editAppearanceCategory(category)
        }

    private suspend fun editAppearanceCategory(category: ApplicationSettings.Appearance) {
        datastore.edit { prefs ->
            prefs[TheColorDataStore.PreferencesKeys.SETTINGS_APPEARANCE_THEME] =
                category.themeOrdinal
        }
    }
}