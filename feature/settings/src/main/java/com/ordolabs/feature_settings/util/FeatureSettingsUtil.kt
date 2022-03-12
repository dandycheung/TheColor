package com.ordolabs.feature_settings.util

import androidx.fragment.app.Fragment
import com.ordolabs.feature_settings.di.FeatureSettingsComponent
import com.ordolabs.thecolor.util.ext.scopedComponentsManager

internal object FeatureSettingsUtil {

    val Fragment.featureSettingsComponent: FeatureSettingsComponent
        get() = scopedComponentsManager.componentOf()
}