package com.ordolabs.settings.ui.fragment

import com.ordolabs.settings.R
import com.ordolabs.thecolor.ui.fragment.BaseFragment as AppBaseFragment

abstract class BaseFragment : AppBaseFragment() {

    override val defaultFragmentContainerId: Int = R.id.defaultFragmentContainer
}