package io.github.mmolosay.thecolor.presentation.settings

import android.content.Context

/**
 * Framework-oriented. //TODO: remove this line across the app
 * Strings that are pre-defined in UI and don't come from ViewModel.
 *
 * This object is created by View, since string resources are tied to platform-specific
 * components (like Context), which should be avoided in ViewModels.
 */
// TODO: rename into "FeatureUiStrings" across the app
data class SettingsUiStrings(
    val topBarTitle: String,
    val topBarGoBackIconDesc: String,
    val itemPreferredColorInputTypeTitle: String,
    val itemPreferredColorInputTypeDesc: String,
    val itemPreferredColorInputTypeValueHex: String,
    val itemPreferredColorInputTypeValueRgb: String,
)

fun SettingsUiStrings(context: Context) =
    SettingsUiStrings(
        topBarTitle = context.getString(R.string.settings_top_bar_title),
        topBarGoBackIconDesc = context.getString(R.string.settings_top_bar_go_back_icon_desc),
        itemPreferredColorInputTypeTitle = context.getString(R.string.settings_item_preferred_color_input_type_title),
        itemPreferredColorInputTypeDesc = context.getString(R.string.settings_item_preferred_color_input_type_desc),
        itemPreferredColorInputTypeValueHex = context.getString(R.string.settings_item_preferred_color_input_type_value_hex),
        itemPreferredColorInputTypeValueRgb = context.getString(R.string.settings_item_preferred_color_input_value_type_rgb),
    )