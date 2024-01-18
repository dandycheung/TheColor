package io.github.mmolosay.thecolor.presentation.home.input.rgb

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.mmolosay.thecolor.presentation.home.input.ColorInputMediator
import io.github.mmolosay.thecolor.presentation.home.input.ColorInputMediator.Command
import io.github.mmolosay.thecolor.presentation.home.input.field.ColorInputFieldUiData
import io.github.mmolosay.thecolor.presentation.home.input.field.ColorInputFieldViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@HiltViewModel(assistedFactory = ColorInputRgbViewModel.Factory::class)
class ColorInputRgbViewModel @AssistedInject constructor(
    @Assisted viewData: ColorInputRgbViewData,
    private val mediator: ColorInputMediator,
) : ViewModel() {

    private val rInputFieldViewModel =
        ColorInputFieldViewModel(
            viewData = viewData.rInputField,
            processText = ::processInput,
        )
    private val gInputFieldViewModel =
        ColorInputFieldViewModel(
            viewData = viewData.gInputField,
            processText = ::processInput,
        )
    private val bInputFieldViewModel =
        ColorInputFieldViewModel(
            viewData = viewData.bInputField,
            processText = ::processInput,
        )

    val uiDataFlow = combine(
        rInputFieldViewModel.uiDataFlow,
        gInputFieldViewModel.uiDataFlow,
        bInputFieldViewModel.uiDataFlow,
        ::makeUiData,
    )
        .onEach { uiData ->
            val areNotAllFieldsEmpty =
                inputFieldViewModels().any { it.uiDataFlow.value.text.isNotEmpty() }
            val command = if (areNotAllFieldsEmpty) {
                val prototype = uiData.assembleColorPrototype()
                Command.Populate(prototype)
            } else {
                Command.Clear
            }
            mediator.issue(command)
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = makeInitialUiData(),
        )

    init {
        collectMediatorColorFlow()
    }

    private fun collectMediatorColorFlow() {
        viewModelScope.launch {
            mediator.rgbCommandFlow.collect { command ->
                when (command) {
                    is Command.Clear -> inputFieldViewModels().forEach { it.clearInputField() }
                    is Command.Populate -> {
                        val newRText = command.color.r?.toString().orEmpty()
                        val newGText = command.color.g?.toString().orEmpty()
                        val newBText = command.color.b?.toString().orEmpty()
                        rInputFieldViewModel.setText(newRText)
                        gInputFieldViewModel.setText(newGText)
                        bInputFieldViewModel.setText(newBText)
                    }
                }
            }
        }
    }

    private fun processInput(text: String): String =
        text
            .filter { it.isDigit() }
            .take(MAX_SYMBOLS_IN_RGB_COMPONENT)

    private fun makeInitialUiData() =
        makeUiData(
            rInputFieldViewModel.uiDataFlow.value,
            gInputFieldViewModel.uiDataFlow.value,
            bInputFieldViewModel.uiDataFlow.value,
        )

    private fun makeUiData(
        rInputField: ColorInputFieldUiData,
        gInputField: ColorInputFieldUiData,
        bInputField: ColorInputFieldUiData,
    ) =
        ColorInputRgbUiData(rInputField, gInputField, bInputField)

    private fun inputFieldViewModels() =
        listOf(rInputFieldViewModel, gInputFieldViewModel, bInputFieldViewModel)

    @AssistedFactory
    interface Factory {
        fun create(viewData: ColorInputRgbViewData): ColorInputRgbViewModel
    }

    private companion object {
        const val MAX_SYMBOLS_IN_RGB_COMPONENT = 3
    }
}