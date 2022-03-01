package com.ordolabs.feature_settings.util

import androidx.fragment.app.Fragment
import com.ordolabs.feature_settings.di.FeatureSettingsComponent
import com.ordolabs.feature_settings.di.FeatureSettingsComponentKeeper

object FeatureSettingsUtil {

    val Fragment.featureSettingsComponent: FeatureSettingsComponent
        get() = when (this) {
            is FeatureSettingsComponentKeeper -> this.featureSettingsComponent
            else -> requireNotNull(this.parentFragment?.featureSettingsComponent)
        }
}