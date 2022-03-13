package com.ordolabs.thecolor.viewmodel

import com.ordolabs.domain.usecase.settings.EditApplicationSettingsUseCase
import com.ordolabs.domain.usecase.settings.GetApplicationSettingsUseCase
import com.ordolabs.thecolor.mapper.toDomain
import com.ordolabs.thecolor.mapper.toPresentation
import com.ordolabs.thecolor.model.settings.ApplicationSettings
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class SettingsViewModel @Inject constructor(
    private val getApplicationSettingsUseCase: GetApplicationSettingsUseCase,
    private val editApplicationSettingsUseCase: EditApplicationSettingsUseCase
) : BaseViewModel() {

    private val _settings = MutableStateFlow<ApplicationSettings?>(null)
    val settings = _settings.asStateFlow()

    init {
        launchInIO {
            getApplicationSettingsUseCase.invoke(Unit)
                .collect { domain ->
                    _settings.emit(domain.toPresentation())
                }
        }
    }

    override fun onCleared() {
        super.onCleared()
    }

    fun editAppearance(updated: ApplicationSettings.Appearance) =
        launchInIO {
            val domain = updated.toDomain()
            editApplicationSettingsUseCase.invoke(domain)
        }
}