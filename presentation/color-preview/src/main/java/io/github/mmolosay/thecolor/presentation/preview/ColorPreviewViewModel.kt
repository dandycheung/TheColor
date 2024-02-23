package io.github.mmolosay.thecolor.presentation.preview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.mmolosay.thecolor.presentation.ColorInputColorProvider
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class ColorPreviewViewModel @Inject constructor(
    private val colorInputColorProvider: ColorInputColorProvider,
) : ViewModel() {

    val stateFlow: StateFlow<State> =
        colorInputColorProvider.colorFlow
            .map { color ->
                if (color != null) {
                    val data = ColorPreviewData(
                        color = ColorPreviewData.ColorInt(0x001122), // TODO: real color
                    )
                    State.Present(data)
                } else {
                    State.Absent
                }
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.Eagerly,
                initialValue = State.Absent,
            )

    sealed interface State {
        data object Absent : State
        data class Present(val data: ColorPreviewData) : State
    }
}