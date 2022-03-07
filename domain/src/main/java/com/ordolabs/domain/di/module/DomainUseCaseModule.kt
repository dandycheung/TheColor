package com.ordolabs.domain.di.module

import com.ordolabs.domain.repository.color.ColorRemoteRepository
import com.ordolabs.domain.repository.color.ColorValidatorRepository
import com.ordolabs.domain.repository.settings.ApplicationSettingsRepository
import com.ordolabs.domain.usecase.color.GetColorDetailsUseCase
import com.ordolabs.domain.usecase.color.GetColorDetailsUseCaseImpl
import com.ordolabs.domain.usecase.color.GetColorSchemeUseCase
import com.ordolabs.domain.usecase.color.GetColorSchemeUseCaseImpl
import com.ordolabs.domain.usecase.color.ValidateColorHexUseCase
import com.ordolabs.domain.usecase.color.ValidateColorHexUseCaseImpl
import com.ordolabs.domain.usecase.color.ValidateColorRgbUseCase
import com.ordolabs.domain.usecase.color.ValidateColorRgbUseCaseImpl
import com.ordolabs.domain.usecase.settings.GetApplicationSettingsUseCase
import com.ordolabs.domain.usecase.settings.GetApplicationSettingsUseCaseImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DomainUseCaseModule {

    // region Color

    @Provides
    @Singleton
    fun provideValidateColorHexUseCase(
        repository: ColorValidatorRepository
    ): ValidateColorHexUseCase =
        ValidateColorHexUseCaseImpl(repository)

    @Provides
    @Singleton
    fun provideValidateColorRgbUseCase(
        repository: ColorValidatorRepository
    ): ValidateColorRgbUseCase =
        ValidateColorRgbUseCaseImpl(repository)

    @Provides
    @Singleton
    fun provideGetColorDetailsUseCase(
        repository: ColorRemoteRepository
    ): GetColorDetailsUseCase =
        GetColorDetailsUseCaseImpl(repository)

    @Provides
    @Singleton
    fun provideGetColorSchemeUseCase(
        repository: ColorRemoteRepository
    ): GetColorSchemeUseCase =
        GetColorSchemeUseCaseImpl(repository)

    // endregion

    // region Settings

    @Provides
    @Singleton
    fun provideGetApplicationSettingsUseCase(
        repository: ApplicationSettingsRepository
    ): GetApplicationSettingsUseCase =
        GetApplicationSettingsUseCaseImpl(repository)

    // endregion
}