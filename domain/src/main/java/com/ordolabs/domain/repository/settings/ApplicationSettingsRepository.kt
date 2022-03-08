package com.ordolabs.domain.repository.settings

import com.ordolabs.domain.model.settings.ApplicationSettings
import kotlinx.coroutines.flow.Flow

interface ApplicationSettingsRepository {

    fun getApplicationSettings(): Flow<ApplicationSettings>
    fun editApplicationSettings(category: ApplicationSettings.Category): Flow<ApplicationSettings>
}