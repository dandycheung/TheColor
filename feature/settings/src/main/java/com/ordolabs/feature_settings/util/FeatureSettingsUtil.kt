package com.ordolabs.feature_settings.util

import android.content.Context
import androidx.fragment.app.Fragment
import com.ordolabs.feature_settings.di.DaggerFeatureSettingsComponent
import com.ordolabs.feature_settings.di.FeatureSettingsComponent
import com.ordolabs.thecolor.util.ContextUtil
import com.ordolabs.thecolor.util.ext.scopedComponentsManager
import com.ordolabs.thecolor.util.struct.scopedcomponent.ScopedComponentStore

internal object FeatureSettingsUtil {

    val Fragment.featureSettingsComponentStore: ScopedComponentStore<*>?
        get() = scopedComponentsManager.storeOf<FeatureSettingsComponent>()

    val Fragment.featureSettingsComponent: FeatureSettingsComponent
        get() = scopedComponentsManager.componentOf()
            ?: assembleFeatureComponent(requireContext()).also { component ->
                scopedComponentsManager.add(component, lifecycle)
            }

    private fun assembleFeatureComponent(context: Context): FeatureSettingsComponent =
        DaggerFeatureSettingsComponent
            .builder()
            .appProvisions(ContextUtil.getAppComponent(context))
            .build()
}