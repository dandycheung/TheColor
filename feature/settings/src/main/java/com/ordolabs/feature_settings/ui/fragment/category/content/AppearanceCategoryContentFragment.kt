package com.ordolabs.feature_settings.ui.fragment.category.content

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import by.kirich1409.viewbindingdelegate.viewBinding
import com.ordolabs.feature_settings.R
import com.ordolabs.feature_settings.databinding.CategoryContentAppearanceFragmentBinding
import com.ordolabs.feature_settings.ui.fragment.BaseFragment

class AppearanceCategoryContentFragment : BaseFragment() {

    private val binding: CategoryContentAppearanceFragmentBinding by viewBinding()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.category_content_appearance_fragment, container, false)
    }

    // region Set views

    override fun setViews() {
        //
    }

    // endregion

    companion object {

        fun newInstance() =
            AppearanceCategoryContentFragment()
    }
}