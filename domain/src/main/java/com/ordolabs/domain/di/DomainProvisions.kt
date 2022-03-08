package com.ordolabs.domain.di

import com.ordolabs.domain.usecase.color.GetColorDetailsUseCase
import com.ordolabs.domain.usecase.color.GetColorSchemeUseCase
import com.ordolabs.domain.usecase.color.ValidateColorHexUseCase
import com.ordolabs.domain.usecase.color.ValidateColorRgbUseCase
import com.ordolabs.domain.usecase.settings.EditApplicationSettingsUseCase
import com.ordolabs.domain.usecase.settings.GetApplicationSettingsUseCase

interface DomainProvisions {

    // region Color use cases

    val validateColorUseCase: ValidateColorHexUseCase
    val validateColorRgbUseCase: ValidateColorRgbUseCase

    val getColorDetailsUseCase: GetColorDetailsUseCase
    val getColorSchemeUseCase: GetColorSchemeUseCase

    // endregion

    // region Settings uses cases

    val getApplicationSettingsUseCase: GetApplicationSettingsUseCase
    val editApplicationSettingsUseCase: EditApplicationSettingsUseCase

    // endregion
}