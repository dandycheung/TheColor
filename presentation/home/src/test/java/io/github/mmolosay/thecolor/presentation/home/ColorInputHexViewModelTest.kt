package io.github.mmolosay.thecolor.presentation.home

import io.github.mmolosay.thecolor.presentation.color.ColorPrototype
import io.github.mmolosay.thecolor.presentation.home.input.ColorInputMediator
import io.github.mmolosay.thecolor.presentation.home.input.field.ColorInputFieldUiData
import io.github.mmolosay.thecolor.presentation.home.input.field.ColorInputFieldUiData.Text
import io.github.mmolosay.thecolor.presentation.home.input.field.ColorInputFieldUiData.ViewData.TrailingIcon
import io.github.mmolosay.thecolor.presentation.home.input.field.ColorInputFieldViewModel.State
import io.github.mmolosay.thecolor.presentation.home.input.hex.ColorInputHexUiData
import io.github.mmolosay.thecolor.presentation.home.input.hex.ColorInputHexViewModel
import io.github.mmolosay.thecolor.testing.MainDispatcherRule
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.verify
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

class ColorInputHexViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    val viewData: ColorInputFieldUiData.ViewData = mockk(relaxed = true) {
        every { trailingIcon } returns TrailingIcon.None
    }

    val mediator: ColorInputMediator = mockk {
        every { hexStateFlow } returns emptyFlow()
        every { send<ColorPrototype.Hex>(any()) } just runs
    }

    lateinit var sut: ColorInputHexViewModel

    val uiData: ColorInputHexUiData
        get() = sut.uiDataFlow.value

    @Test
    fun `filtering keeps only digits and letters A-F`() = runTest {
        createSut()

        val result = uiData.inputField.filterUserInput("123abc_!.@ABG")

        result.string shouldBe "123AB"
    }

    @Test
    fun `filtering keeps only first 6 characters`() = runTest {
        createSut()

        val result = uiData.inputField.filterUserInput("123456789ABCDEF")

        result.string shouldBe "123456"
    }

    @Test
    fun `populated state is sent to mediator on new UiData with non-empty text in input`() =
        runTest(mainDispatcherRule.testDispatcher) {
            createSut()
            val collectionJob = launch {
                sut.uiDataFlow.collect() // subscriber to activate the flow
            }

            // first State.Empty is emitted on subscription due to initial text=""
            uiData.inputField.onTextChange(Text("1F")) // non-empty text

            val sentState = State.Populated(ColorPrototype.Hex("1F"))
            verify(exactly = 1) { mediator.send(sentState) }
            collectionJob.cancel()
        }

    @Test
    fun `empty state is sent to mediator on new UiData with no text in input`() =
        runTest(mainDispatcherRule.testDispatcher) {
            createSut()
            val collectionJob = launch {
                sut.uiDataFlow.collect() // subscriber to activate the flow
            }

            // first State.Empty is emitted on subscription due to initial text=""
            uiData.inputField.onTextChange(Text("1F")) // non-empty text
            uiData.inputField.onTextChange(Text("")) // empty text, second State.Empty

            verify(exactly = 2) { mediator.send(State.Empty) }
            collectionJob.cancel()
        }

    @Test
    fun `empty state from mediator clears input text`() =
        runTest(mainDispatcherRule.testDispatcher) {
            val initialState = State.Populated(ColorPrototype.Hex("not empty"))
            val hexStateFlow = MutableStateFlow<State<ColorPrototype.Hex>>(initialState)
            every { mediator.hexStateFlow } returns hexStateFlow
            createSut()
            val collectionJob = launch {
                sut.uiDataFlow.collect() // subscriber to activate the flow
            }

            // initially is not empty
            hexStateFlow.value = State.Empty

            uiData.inputField.text.string shouldBe ""
            collectionJob.cancel()
        }

    @Test
    fun `populated state from mediator updates input text`() =
        runTest(mainDispatcherRule.testDispatcher) {
            val initialState = State.Empty
            val hexStateFlow = MutableStateFlow<State<ColorPrototype.Hex>>(initialState)
            every { mediator.hexStateFlow } returns hexStateFlow
            createSut()
            val collectionJob = launch {
                sut.uiDataFlow.collect() // subscriber to activate the flow
            }

            // initial text is empty
            hexStateFlow.value = State.Populated(ColorPrototype.Hex("anything"))

            uiData.inputField.text.string shouldBe "anything"
            collectionJob.cancel()
        }

    fun createSut() =
        ColorInputHexViewModel(
            viewData = viewData,
            mediator = mediator,
            defaultDispatcher = mainDispatcherRule.testDispatcher,
        ).also {
            sut = it
        }
}