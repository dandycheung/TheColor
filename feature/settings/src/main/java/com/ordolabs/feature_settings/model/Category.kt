package com.ordolabs.feature_settings.model

import android.os.Parcelable
import androidx.annotation.DrawableRes
import androidx.annotation.Keep
import androidx.annotation.StringRes
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class Category(
    val contentType: ContentType,
    @StringRes val titleRes: Int,
    @DrawableRes val iconRes: Int
) : Parcelable {

    enum class ContentType {
        APPEARANCE,
        ABOUT
    }
}