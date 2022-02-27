package com.ordolabs.feature_settings.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.ordolabs.feature_settings.R
import com.ordolabs.feature_settings.databinding.SettingsFragmentBinding
import com.ordolabs.feature_settings.ui.fragment.category.content.ContentType
import com.ordolabs.thecolor.util.ext.setActivitySupportActionBar

class SettingsFragment : BaseFragment() {

    private val binding: SettingsFragmentBinding by viewBinding()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.settings_fragment, container, false)
    }

    // region Set views

    override fun setViews() {
        setToolbar()
        setCategories()
    }

    private fun setToolbar() =
        binding.run {
            setActivitySupportActionBar(toolbar)
            toolbar.setNavigationOnClickListener(::onToolbarNavigationClick)
        }

    private fun setCategories() =
        binding.run {
            appearanceCategory.setOnClickListener { view ->
                navigateToCategory(view)
            }
        }

    // endregion

    // region View actions

    @Suppress("UNUSED_PARAMETER")
    private fun onToolbarNavigationClick(view: View) {
        findNavController().navigateUp()
    }

    // endregion

    private fun navigateToCategory(itemView: View) {
        val contentType = ContentType.APPEARANCE
        val action = SettingsFragmentDirections.categoryAction(contentType)
        val extras = FragmentNavigatorExtras(
            itemView to getString(R.string.settings_category_shared_title)
        )
        findNavController().navigate(action, extras)
    }

    companion object {
        // being created by NavHostFragment, thus no newInstance() method
    }
}