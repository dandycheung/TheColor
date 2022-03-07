package com.ordolabs.domain.usecase.settings

import com.ordolabs.domain.model.settings.ApplicationSettings
import com.ordolabs.domain.repository.settings.ApplicationSettingsRepository
import com.ordolabs.domain.usecase.BaseUseCase
import kotlinx.coroutines.flow.Flow

interface GetApplicationSettingsUseCase : BaseUseCase<Unit, ApplicationSettings>

class GetApplicationSettingsUseCaseImpl(
    private val settingsRepository: ApplicationSettingsRepository
) : GetApplicationSettingsUseCase {

    override suspend fun invoke(param: Unit): Flow<ApplicationSettings> =
        settingsRepository.getApplicationSettings()
}