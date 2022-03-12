package com.ordolabs.feature_settings.ui.fragment

import android.content.Context
import com.ordolabs.feature_settings.R
import com.ordolabs.feature_settings.util.FeatureSettingsUtil.featureSettingsComponentStore
import com.ordolabs.thecolor.ui.fragment.BaseFragment as AppBaseFragment

abstract class BaseFragment : AppBaseFragment() {

    override val defaultFragmentContainerId: Int = R.id.defaultFragmentContainer

    override fun onAttach(context: Context) {
        super.onAttach(context)
        featureSettingsComponentStore?.subscribe(lifecycle)
    }
}