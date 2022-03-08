package com.ordolabs.domain.usecase.settings

import com.ordolabs.domain.model.settings.ApplicationSettings
import com.ordolabs.domain.repository.settings.ApplicationSettingsRepository
import com.ordolabs.domain.usecase.BaseUseCase

interface EditApplicationSettingsUseCase :
    BaseUseCase<ApplicationSettings.Category, Unit>

class EditApplicationSettingsUseCaseImpl(
    private val settingsRepository: ApplicationSettingsRepository
) : EditApplicationSettingsUseCase {

    override suspend fun invoke(param: ApplicationSettings.Category) =
        settingsRepository.editApplicationSettings(param)
}