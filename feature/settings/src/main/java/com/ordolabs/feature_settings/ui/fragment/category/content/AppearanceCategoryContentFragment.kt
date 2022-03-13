package com.ordolabs.feature_settings.ui.fragment.category.content

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatDelegate
import by.kirich1409.viewbindingdelegate.viewBinding
import com.ordolabs.feature_settings.R
import com.ordolabs.feature_settings.databinding.CategoryContentAppearanceFragmentBinding
import com.ordolabs.thecolor.model.settings.ApplicationSettings
import com.ordolabs.thecolor.model.settings.ApplicationSettings.Appearance

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
        setThemeRadioButtons()
    }

    private fun setThemeRadioButtons() {
        binding.themeSystem.setOnCheckedChangeListener(::onThemeRadioButtonChecked)
        binding.themeLight.setOnCheckedChangeListener(::onThemeRadioButtonChecked)
        binding.themeDark.setOnCheckedChangeListener(::onThemeRadioButtonChecked)
    }

    // endregion

    // region View actions

    private fun onThemeRadioButtonChecked(button: CompoundButton, isChecked: Boolean) {
        if (!isChecked) return // ignore unchecking
        val settings = settings ?: return
        val theme = getThemeForRadioButton(button)
        val mode = settings.appearance.themeNightMode
        AppCompatDelegate.setDefaultNightMode(mode)
        val updated = settings.appearance.copy(theme = theme)
        settingsVM.editAppearance(updated)
    }

    // endregion

    // region View utils

    private fun getRadioButtonForTheme(theme: Appearance.Theme): RadioButton =
        when (theme) {
            Appearance.Theme.SYSTEM -> binding.themeSystem
            Appearance.Theme.LIGHT -> binding.themeLight
            Appearance.Theme.DARK -> binding.themeDark
        }

    private fun getThemeForRadioButton(radiobutton: View): Appearance.Theme =
        when (radiobutton) {
            binding.themeLight -> Appearance.Theme.LIGHT
            binding.themeDark -> Appearance.Theme.DARK
            else -> Appearance.Theme.SYSTEM
        }

    // endregion

    // region BaseCategoryContentFragment

    override fun populateViews(settings: ApplicationSettings) {
        populateTheme(settings.appearance.theme)
    }

    private fun populateTheme(current: Appearance.Theme) {
        val radiobutton = getRadioButtonForTheme(current)
        radiobutton.isChecked = true
        radiobutton.jumpDrawablesToCurrentState() // skips animation
    }

    // endregion

    companion object {

        fun newInstance() =
            AppearanceCategoryContentFragment()
    }
}