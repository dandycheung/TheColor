package com.ordolabs.feature_settings.ui.fragment.category.content

import androidx.annotation.CallSuper
import androidx.fragment.app.viewModels
import com.ordolabs.feature_settings.model.ApplicationSettings
import com.ordolabs.feature_settings.ui.fragment.BaseFragment
import com.ordolabs.feature_settings.util.FeatureSettingsUtil.featureSettingsComponent
import com.ordolabs.feature_settings.viewmodel.SettingsViewModel

abstract class BaseCategoryContentFragment : BaseFragment() {

    // region Abstract

    abstract fun onSettingsCollected(settings: ApplicationSettings?)

    // endregion

    protected val settingsVM: SettingsViewModel by viewModels {
        // TODO: has no feature settings component in ancestors; solve
        featureSettingsComponent.viewModelFactory
    }

    // region Collect ViewModels data

    @CallSuper
    override fun collectViewModelsData() {
        collectSettings()
    }

    private fun collectSettings() =
        settingsVM.settings.collectOnLifecycle(action = ::onSettingsCollected)

    // endregion
}