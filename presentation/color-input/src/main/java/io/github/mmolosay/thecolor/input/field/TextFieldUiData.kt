package io.github.mmolosay.thecolor.input.field

/**
 * @param onTextChange a callback to be invoked when displayed text is changed.
 * It should receive a result of [filterUserInput] or other text that is presentation-ready.
 *
 * @param filterUserInput filters text from user input and returns processed text to be displayed.
 */
data class TextFieldUiData(
    val text: Text,
    val onTextChange: (Text) -> Unit,
    val filterUserInput: (String) -> Text,
    val label: String,
    val placeholder: String,
    val prefix: String?,
    val trailingButton: TrailingButton,
) {

    /**
     * Text that was filtered and is ready to be displayed in UI.
     * Can be obtained from [filterUserInput].
     *
     * The main point of having this `value class` is to avoid accidents with data types.
     * Function [filterUserInput] takes String from UI and returns filtered [Text].
     * At the same time, [onTextChange] takes [Text], not String.
     * This depicts that [onTextChange] should receive preemptively filtered text from [filterUserInput].
     */
    @JvmInline
    value class Text(val string: String)
    // I decided to not introduce 'Text.Empty' extension for the same reason why there's no String.Empty

    sealed interface TrailingButton {
        data object Hidden : TrailingButton
        data class Visible(
            val onClick: () -> Unit,
            val iconContentDesc: String,
        ) : TrailingButton
    }

    /**
     * Part of to-be [TextFieldUiData].
     * Created by `View`, since string resources are tied to platform-specific
     * components (like `Context`), which should be avoided in `ViewModel`s.
     */
    data class ViewData(
        val label: String,
        val placeholder: String,
        val prefix: String?,
        val trailingIcon: TrailingIcon,
    ) {

        sealed interface TrailingIcon {
            data object None : TrailingIcon
            data class Exists(val contentDesc: String) : TrailingIcon
        }
    }
}