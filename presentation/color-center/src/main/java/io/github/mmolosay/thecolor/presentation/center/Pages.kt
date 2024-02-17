package io.github.mmolosay.thecolor.presentation.center

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowLeft
import androidx.compose.material.icons.rounded.KeyboardArrowRight
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp
import io.github.mmolosay.thecolor.presentation.design.colorsOnTintedSurface

@Composable
fun DetailsPage(
    content: @Composable () -> Unit,
    uiData: ColorCenterUiData.Page,
) =
    Page(
        content = content,
        changePageButton = {
            ChangePageButton(
                uiData = uiData.changePageButton,
                iconPlacement = IconPlacement.Trailing,
                iconImageVector = Icons.Rounded.KeyboardArrowRight,
            )
        },
    )

@Composable
fun SchemePage(
    content: @Composable () -> Unit,
    uiData: ColorCenterUiData.Page,
) =
    Page(
        content = content,
        changePageButton = {
            ChangePageButton(
                uiData = uiData.changePageButton,
                iconPlacement = IconPlacement.Leading,
                iconImageVector = Icons.Rounded.KeyboardArrowLeft,
            )
        },
    )

@Composable
internal fun Page(
    content: @Composable () -> Unit,
    changePageButton: @Composable () -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        content()
        Spacer(modifier = Modifier.height(16.dp)) // bounded min height
        Spacer(modifier = Modifier.weight(1f)) // unbounded max height
        changePageButton()
    }
}

/*
 * There's a curious separation of data, that's required by this function.
 * Parameter 'uiData' is injectable TODO: finish me
 */
@Composable
internal fun ChangePageButton(
    uiData: ColorCenterUiData.ChangePageButton,
    iconPlacement: IconPlacement,
    iconImageVector: ImageVector,
) {
    @Composable
    fun Icon() =
        androidx.compose.material3.Icon(
            imageVector = iconImageVector,
            contentDescription = null, // described by neighboring text
        )

    val colors = ButtonDefaults.outlinedButtonColors(
        contentColor = colorsOnTintedSurface.accent,
    )
    val border = ButtonDefaults.outlinedButtonBorder.copy(
        brush = SolidColor(colorsOnTintedSurface.accent),
    )
    val addedTextStyle = TextStyle(
        platformStyle = PlatformTextStyle(
            includeFontPadding = false,
        ),
        lineHeightStyle = LineHeightStyle(
            alignment = LineHeightStyle.Alignment.Proportional,
            trim = LineHeightStyle.Trim.FirstLineTop,
        )
    )
    OutlinedButton(
        onClick = uiData.onClick,
        colors = colors,
        border = border,
    ) {
        // TODO: I don't like this solution with two 'if's for icon placement; redesign
        if (iconPlacement == IconPlacement.Leading) {
            Icon()
        }
        Text(
            text = uiData.text,
            style = LocalTextStyle.current.merge(addedTextStyle),
        )
        if (iconPlacement == IconPlacement.Trailing) {
            Icon()
        }
    }
}

enum class IconPlacement {
    Leading, Trailing,
}