package com.ordolabs.feature_settings.ui.fragment

import com.ordolabs.feature_settings.R
import com.ordolabs.thecolor.ui.fragment.BaseFragment as AppBaseFragment

abstract class BaseFragment : AppBaseFragment() {

    override val defaultFragmentContainerId: Int = R.id.defaultFragmentContainer
}