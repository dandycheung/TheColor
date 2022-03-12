package com.ordolabs.feature_home.util

import androidx.fragment.app.Fragment
import com.ordolabs.feature_home.di.FeatureHomeComponent
import com.ordolabs.thecolor.util.ext.scopedComponentsManager

internal object FeatureHomeUtil {

    val Fragment.featureHomeComponent: FeatureHomeComponent
        get() = scopedComponentsManager.componentOf()
}