package com.ordolabs.feature_home.util

import android.content.Context
import androidx.fragment.app.Fragment
import com.ordolabs.feature_home.di.DaggerFeatureHomeComponent
import com.ordolabs.feature_home.di.FeatureHomeComponent
import com.ordolabs.thecolor.util.ContextUtil
import com.ordolabs.thecolor.util.ext.scopedComponentsManager

internal object FeatureHomeUtil {

    val Fragment.featureHomeComponent: FeatureHomeComponent
        get() = scopedComponentsManager.componentOf()
            ?: assembleFeatureComponent(requireContext()).also { component ->
                scopedComponentsManager.add(component, lifecycle)
            }

    private fun assembleFeatureComponent(context: Context): FeatureHomeComponent =
        DaggerFeatureHomeComponent
            .builder()
            .appProvisions(ContextUtil.getAppComponent(context))
            .build()

}