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
        val theme = getThemeForRadioButton(button)
        val mode = when (theme) {
            ApplicationSettings.Appearance.Theme.SYSTEM -> AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
            ApplicationSettings.Appearance.Theme.LIGHT -> AppCompatDelegate.MODE_NIGHT_NO
            ApplicationSettings.Appearance.Theme.DARK -> AppCompatDelegate.MODE_NIGHT_YES
        }
        AppCompatDelegate.setDefaultNightMode(mode)
        val updated = settings?.appearance?.copy(theme = theme) ?: return
        settingsVM.editAppearance(updated)
    }

    // endregion

    // region View utils

    private fun getRadioButtonForTheme(theme: ApplicationSettings.Appearance.Theme): RadioButton =
        when (theme) {
            ApplicationSettings.Appearance.Theme.SYSTEM -> binding.themeSystem
            ApplicationSettings.Appearance.Theme.LIGHT -> binding.themeLight
            ApplicationSettings.Appearance.Theme.DARK -> binding.themeDark
        }

    private fun getThemeForRadioButton(radiobutton: View): ApplicationSettings.Appearance.Theme =
        when (radiobutton) {
            binding.themeLight -> ApplicationSettings.Appearance.Theme.LIGHT
            binding.themeDark -> ApplicationSettings.Appearance.Theme.DARK
            else -> ApplicationSettings.Appearance.Theme.SYSTEM
        }

    // endregion

    // region BaseCategoryContentFragment

    override fun populateViews(settings: ApplicationSettings) {
        populateTheme(settings.appearance.theme)
    }

    private fun populateTheme(current: ApplicationSettings.Appearance.Theme) {
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