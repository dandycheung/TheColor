package com.ordolabs.feature_settings.ui.fragment.category.content

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import by.kirich1409.viewbindingdelegate.viewBinding
import com.ordolabs.feature_settings.R
import com.ordolabs.feature_settings.databinding.CategoryContentAppearanceFragmentBinding
import com.ordolabs.feature_settings.model.ApplicationSettings

class AppearanceCategoryContentFragment : BaseCategoryContentFragment() {

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

    // region Populate views

    private fun populateViews(appearance: ApplicationSettings.Appearance) {
        populateTheme(appearance.theme)
    }

    private fun populateTheme(current: ApplicationSettings.Appearance.Theme) {
        val radiobutton = getRadioButtonForTheme(current)
        radiobutton.isChecked = true
        radiobutton.jumpDrawablesToCurrentState() // skips animation
    }

    // endregion

    // region View utils

    private fun getRadioButtonForTheme(theme: ApplicationSettings.Appearance.Theme): RadioButton =
        when (theme) {
            ApplicationSettings.Appearance.Theme.SYSTEM -> binding.themeSystem
            ApplicationSettings.Appearance.Theme.LIGHT -> binding.themeLight
            ApplicationSettings.Appearance.Theme.DARK -> binding.themeDark
        }

    // endregion

    // region BaseCategoryContentFragment

    override fun onSettingsCollected(settings: ApplicationSettings?) {
        settings ?: return
        populateViews(settings.appearance)
    }

    // endregion

    companion object {

        fun newInstance() =
            AppearanceCategoryContentFragment()
    }
}