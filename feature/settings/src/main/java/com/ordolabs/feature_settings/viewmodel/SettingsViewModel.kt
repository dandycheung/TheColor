package com.ordolabs.feature_settings.viewmodel

import com.ordolabs.domain.usecase.settings.GetApplicationSettingsUseCase
import com.ordolabs.feature_settings.mapper.toPresentation
import com.ordolabs.feature_settings.model.ApplicationSettings
import com.ordolabs.thecolor.viewmodel.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class SettingsViewModel @Inject constructor(
    getApplicationSettingsUseCase: GetApplicationSettingsUseCase
) : BaseViewModel() {

    private val _settings = MutableStateFlow<ApplicationSettings?>(null)
    val settings = _settings.asStateFlow()

    init {
        launchInIO {
            getApplicationSettingsUseCase.invoke(Unit).collect { domain ->
                _settings.emit(domain.toPresentation())
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
    }
}