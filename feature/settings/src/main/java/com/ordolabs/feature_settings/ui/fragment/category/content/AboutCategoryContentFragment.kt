package com.ordolabs.feature_settings.ui.fragment.category.content

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import by.kirich1409.viewbindingdelegate.viewBinding
import com.ordolabs.feature_settings.R
import com.ordolabs.feature_settings.databinding.CategoryContentAboutFragmentBinding
import com.ordolabs.thecolor.model.settings.ApplicationSettings

class AboutCategoryContentFragment : BaseCategoryContentFragment() {

    private val binding: CategoryContentAboutFragmentBinding by viewBinding()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.category_content_about_fragment, container, false)
    }

    // region Set views

    override fun setViews() {
        //
    }

    // endregion

    // region BaseCategoryContentFragment

    override fun populateViews(settings: ApplicationSettings) {
        // do nothing, since there is no mutable settings in this fragment
    }

    // endregion

    companion object {

        fun newInstance() =
            AboutCategoryContentFragment()
    }
}