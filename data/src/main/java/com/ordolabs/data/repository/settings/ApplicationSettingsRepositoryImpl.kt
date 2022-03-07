package com.ordolabs.data.repository.settings

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
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
}