package com.ordolabs.feature_home.ui.fragment.color.data.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ordolabs.feature_home.R
import io.github.mmolosay.presentation.fragment.BaseFragment
import io.github.mmolosay.presentation.util.InflaterUtil.cloneInViewContext

class ColorDetailsShimmerFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // inherit container view group theme
        return inflater.cloneInViewContext(container)
            .inflate(R.layout.color_details_shimmer_fragment, container, false)
    }

    override fun collectViewModelsData() {
        // nothing is here
    }

    override fun setViews() {
        // nothing is here
    }

    companion object {
        fun newInstance() =
            ColorDetailsShimmerFragment()
    }
}