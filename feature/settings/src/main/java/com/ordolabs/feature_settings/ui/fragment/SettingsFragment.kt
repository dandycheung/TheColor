package com.ordolabs.feature_settings.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.ordolabs.feature_settings.R
import com.ordolabs.feature_settings.databinding.SettingsFragmentBinding
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
    }

    private fun setToolbar() =
        binding.run {
            setActivitySupportActionBar(toolbar)
            toolbar.setNavigationOnClickListener(::onToolbarNavigationClick)
        }

    // endregion

    // region View actions

    @Suppress("UNUSED_PARAMETER")
    private fun onToolbarNavigationClick(view: View) {
        findNavController().navigateUp()
    }

    // endregion

    companion object {
        // being created by NavHostFragment, thus no newInstance() method
    }
}