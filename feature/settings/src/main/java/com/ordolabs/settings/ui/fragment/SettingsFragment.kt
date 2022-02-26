package com.ordolabs.settings.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import by.kirich1409.viewbindingdelegate.viewBinding
import com.ordolabs.settings.R
import com.ordolabs.settings.databinding.SettingsFragmentBinding

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
        //
    }

    // endregion

    companion object {
        // being created by NavHostFragment, thus no newInstance() method
    }
}