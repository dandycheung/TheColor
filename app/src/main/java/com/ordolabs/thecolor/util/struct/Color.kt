package com.ordolabs.thecolor.util.struct

import android.os.Parcelable
import androidx.annotation.ColorInt
import androidx.annotation.IntRange
import com.github.ajalt.colormath.RGB
import com.ordolabs.thecolor.model.InputHexPresentation
import com.ordolabs.thecolor.model.InputRgbPresentation
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

/**
 * "Abstract" color model. Actually, holds HEX color value __without number sign__.
 * __Always__ represents a valid color.
 *
 * @param [hex] HEX color string __without__ number sign, e.g. "16A8C0".
 */
@Parcelize
data class Color(
    val hex: String
) : Parcelable {

    @IgnoredOnParcel
    val hexWithNumberSign: String by lazy { '#' + this.hex }

    override fun equals(other: Any?): Boolean {
        other ?: return false
        if (other is String) {
            return (other == hex || other == hexWithNumberSign)
        }
        if (other is Color) {
            return super.equals(other)
        }
        return false
    }

    override fun hashCode(): Int {
        return hex.hashCode()
    }

    companion object
}

fun Color.Companion.from(input: InputHexPresentation): Color? {
    input.value ?: return null
    val expanded = when (input.value.length) {
        3 -> input.value.map { it.toString().repeat(2) }.joinToString(separator = "")
        6 -> input.value
        else -> return null
    }
    return Color(hex = expanded)
}

fun Color.Companion.from(input: InputRgbPresentation): Color? {
    if (input.r == null || input.g == null || input.b == null) return null
    val rgb = RGB(input.r, input.g, input.b)
    val value = rgb.toHex(withNumberSign = false).uppercase()
    return Color(hex = value)
}

fun Color.toColorHex(): InputHexPresentation {
    return InputHexPresentation(value = this.hex)
}

fun Color.toColorRgb(): InputRgbPresentation {
    val color = RGB(this.hex)
    return InputRgbPresentation(
        r = color.r,
        g = color.g,
        b = color.b
    )
}

@ColorInt
fun Color.toColorInt(): Int {
    return android.graphics.Color.parseColor(this.hexWithNumberSign)
}

fun Color.isDark(
    @IntRange(from = 0, to = 100) threshold: Int = 60
): Boolean {
    val hsl = RGB(this.hex).toHSL()
    return (hsl.l <= threshold)
}