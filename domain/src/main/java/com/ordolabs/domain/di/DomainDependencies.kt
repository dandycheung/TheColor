package com.ordolabs.domain.di

import com.ordolabs.domain.repository.color.ColorRemoteRepository
import com.ordolabs.domain.repository.color.ColorValidatorRepository
import com.ordolabs.domain.repository.settings.ApplicationSettingsRepository

/**
 * DIP in order to make ':domain' module absolutely stable.
 */
interface DomainDependencies {

    // region Repositories

    val colorValidatorRepository: ColorValidatorRepository
    val colorRemoteRepository: ColorRemoteRepository

    val applicationSettingsRepository: ApplicationSettingsRepository

    // endregion
}