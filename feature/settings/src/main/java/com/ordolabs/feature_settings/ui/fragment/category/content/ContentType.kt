package com.ordolabs.feature_settings.ui.fragment.category.content

import androidx.annotation.Keep
import androidx.annotation.StringRes
import com.ordolabs.feature_settings.R

@Keep
enum class ContentType(@StringRes val titleRes: Int) {
    APPEARANCE(R.string.settings_category_appearance)
}