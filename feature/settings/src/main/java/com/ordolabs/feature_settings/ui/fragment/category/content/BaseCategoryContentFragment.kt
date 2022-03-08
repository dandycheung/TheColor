package com.ordolabs.feature_settings.ui.fragment.category.content

import androidx.annotation.CallSuper
import androidx.fragment.app.viewModels
import com.ordolabs.feature_settings.ui.fragment.BaseFragment
import com.ordolabs.feature_settings.util.FeatureSettingsUtil.featureSettingsComponent
import com.ordolabs.thecolor.model.settings.ApplicationSettings
import com.ordolabs.thecolor.viewmodel.SettingsViewModel

abstract class BaseCategoryContentFragment : BaseFragment() {

    // region Abstract

    abstract fun populateViews(settings: ApplicationSettings)

    // endregion

    protected val settingsVM: SettingsViewModel by viewModels {
        featureSettingsComponent.viewModelFactory
    }

    protected var settings: ApplicationSettings? = null

    // region Collect ViewModels data

    @CallSuper
    override fun collectViewModelsData() {
        collectSettings()
    }

    private fun collectSettings() =
        settingsVM.settings.collectOnLifecycle a@{ settings ->
            settings ?: return@a
            populateViews(settings)
            this.settings = settings
        }

    // endregion
}