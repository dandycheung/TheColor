package io.github.mmolosay.thecolor.presentation.scheme

import io.github.mmolosay.thecolor.domain.model.Color
import io.github.mmolosay.thecolor.domain.model.ColorScheme.Mode
import io.github.mmolosay.thecolor.domain.usecase.GetColorSchemeUseCase
import io.github.mmolosay.thecolor.presentation.scheme.ColorSchemeData.ApplyChangesButton
import io.github.mmolosay.thecolor.presentation.scheme.ColorSchemeData.SwatchCount
import io.github.mmolosay.thecolor.presentation.scheme.ColorSchemeViewModel.State
import io.github.mmolosay.thecolor.testing.MainDispatcherRule
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.beOfType
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

class ColorSchemeViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    val getInitialState: GetInitialStateUseCase = mockk()
    val getColorScheme: GetColorSchemeUseCase = mockk()
    val colorSchemeDataFactory: ColorSchemeDataFactory = mockk()

    lateinit var sut: ColorSchemeViewModel

    val ColorSchemeViewModel.data: ColorSchemeData
        get() {
            this.dataStateFlow.value should beOfType<State.Ready>() // // assertion for clear failure message
            return (this.dataStateFlow.value as State.Ready).data
        }

    @Test
    fun `initial data state is as provided`() {
        every { getInitialState() } returns State.Loading

        createSut()

        sut.dataStateFlow.value should beOfType<State.Loading>()
    }

    @Test
    fun `call to 'get color scheme' emits Loading state from flow`() =
        runTest(mainDispatcherRule.testDispatcher) {
            val data: ColorSchemeData = mockk {
                every { selectedMode } returns Mode.Monochrome
                every { selectedSwatchCount } returns SwatchCount.Six
            }
            every { getInitialState() } returns State.Ready(data)
            coEvery { getColorScheme(request = any<GetColorSchemeUseCase.Request>()) } returns mockk()
            every {
                colorSchemeDataFactory.create(
                    scheme = any(),
                    config = any(),
                    onModeSelect = any(),
                    onSwatchCountSelect = any(),
                )
            } returns mockk()
            createSut()

            // "then" block
            launch {
                sut.dataStateFlow
                    .drop(1) // replayed initial state
                    .first() should beOfType<State.Loading>()
            }

            // "when" block
            sut.getColorScheme(seed = mockk())
        }

    @Test
    fun `call to 'get color scheme' emits Ready state from flow`() =
        runTest(mainDispatcherRule.testDispatcher) {
            every { getInitialState() } returns State.Loading
            coEvery { getColorScheme(request = any<GetColorSchemeUseCase.Request>()) } returns mockk()
            every {
                colorSchemeDataFactory.create(
                    scheme = any(),
                    config = any(),
                    onModeSelect = any(),
                    onSwatchCountSelect = any(),
                )
            } returns mockk()
            createSut()

            sut.getColorScheme(seed = mockk())

            sut.dataStateFlow.value should beOfType<State.Ready>()
        }

    @Test
    fun `selecting new mode updates selected mode`() =
        runTest(mainDispatcherRule.testDispatcher) {
            every { getInitialState() } returns State.Loading
            coEvery { getColorScheme(request = any<GetColorSchemeUseCase.Request>()) } returns mockk()
            val onModeSelectSlot = slot<(Mode) -> Unit>()
            every {
                colorSchemeDataFactory.create(
                    scheme = any(),
                    config = any(),
                    onModeSelect = capture(onModeSelectSlot),
                    onSwatchCountSelect = any(),
                )
            } answers {
                ColorSchemeData(
                    swatches = listOf(),
                    activeMode = Mode.Monochrome,
                    selectedMode = Mode.Monochrome,
                    onModeSelect = onModeSelectSlot.captured,
                    activeSwatchCount = SwatchCount.Six,
                    selectedSwatchCount = SwatchCount.Six,
                    onSwatchCountSelect = {},
                    applyChangesButton = ApplyChangesButton.Hidden,
                )
            }
            createSut()
            sut.getColorScheme(seed = mockk()) // from other tests we know that state will become Ready after this call

            sut.data.onModeSelect(Mode.Analogic)

            sut.data.selectedMode shouldBe Mode.Analogic
        }

    @Test
    fun `selecting new mode that is different from the active mode makes 'apply changes' button visible`() =
        runTest(mainDispatcherRule.testDispatcher) {
            every { getInitialState() } returns State.Loading
            coEvery { getColorScheme(request = any<GetColorSchemeUseCase.Request>()) } returns mockk()
            val onModeSelectSlot = slot<(Mode) -> Unit>()
            every {
                colorSchemeDataFactory.create(
                    scheme = any(),
                    config = any(),
                    onModeSelect = capture(onModeSelectSlot),
                    onSwatchCountSelect = any(),
                )
            } answers {
                ColorSchemeData(
                    swatches = listOf(),
                    activeMode = Mode.Monochrome,
                    selectedMode = Mode.Monochrome,
                    onModeSelect = onModeSelectSlot.captured,
                    activeSwatchCount = SwatchCount.Six,
                    selectedSwatchCount = SwatchCount.Six,
                    onSwatchCountSelect = {},
                    applyChangesButton = ApplyChangesButton.Hidden,
                )
            }
            createSut()
            sut.getColorScheme(seed = mockk()) // from other tests we know that state will become Ready after this call

            sut.data.onModeSelect(Mode.Analogic)

            sut.data.applyChangesButton should beOfType<ApplyChangesButton.Visible>()
        }

    @Test
    fun `selecting new mode that is same as the active mode makes 'apply changes' button hidden`() =
        runTest(mainDispatcherRule.testDispatcher) {
            every { getInitialState() } returns State.Loading
            coEvery { getColorScheme(request = any<GetColorSchemeUseCase.Request>()) } returns mockk()
            val onModeSelectSlot = slot<(Mode) -> Unit>()
            every {
                colorSchemeDataFactory.create(
                    scheme = any(),
                    config = any(),
                    onModeSelect = capture(onModeSelectSlot),
                    onSwatchCountSelect = any(),
                )
            } answers {
                ColorSchemeData(
                    swatches = listOf(),
                    activeMode = Mode.Monochrome,
                    selectedMode = Mode.Analogic,
                    onModeSelect = onModeSelectSlot.captured,
                    activeSwatchCount = SwatchCount.Six,
                    selectedSwatchCount = SwatchCount.Six,
                    onSwatchCountSelect = {},
                    applyChangesButton = ApplyChangesButton.Hidden,
                )
            }
            createSut()
            sut.getColorScheme(seed = mockk()) // from other tests we know that state will become Ready after this call

            sut.data.onModeSelect(Mode.Monochrome)

            sut.data.applyChangesButton should beOfType<ApplyChangesButton.Hidden>()
        }

    @Test
    fun `selecting new swatch count updates selected swatch count`() =
        runTest(mainDispatcherRule.testDispatcher) {
            every { getInitialState() } returns State.Loading
            coEvery { getColorScheme(request = any<GetColorSchemeUseCase.Request>()) } returns mockk()
            val onSwatchCountSelectSlot = slot<(SwatchCount) -> Unit>()
            every {
                colorSchemeDataFactory.create(
                    scheme = any(),
                    config = any(),
                    onModeSelect = any(),
                    onSwatchCountSelect = capture(onSwatchCountSelectSlot),
                )
            } answers {
                ColorSchemeData(
                    swatches = listOf(),
                    activeMode = Mode.Monochrome,
                    selectedMode = Mode.Monochrome,
                    onModeSelect = {},
                    activeSwatchCount = SwatchCount.Six,
                    selectedSwatchCount = SwatchCount.Six,
                    onSwatchCountSelect = onSwatchCountSelectSlot.captured,
                    applyChangesButton = ApplyChangesButton.Hidden,
                )
            }
            createSut()
            sut.getColorScheme(seed = mockk()) // from other tests we know that state will become Ready after this call

            sut.data.onSwatchCountSelect(SwatchCount.Thirteen)

            sut.data.selectedSwatchCount shouldBe SwatchCount.Thirteen
        }

    @Test
    fun `selecting new swatch count that is different from the active swatch count makes 'apply changes' button visible`() =
        runTest(mainDispatcherRule.testDispatcher) {
            every { getInitialState() } returns State.Loading
            coEvery { getColorScheme(request = any<GetColorSchemeUseCase.Request>()) } returns mockk()
            val onSwatchCountSelectSlot = slot<(SwatchCount) -> Unit>()
            every {
                colorSchemeDataFactory.create(
                    scheme = any(),
                    config = any(),
                    onModeSelect = any(),
                    onSwatchCountSelect = capture(onSwatchCountSelectSlot),
                )
            } answers {
                ColorSchemeData(
                    swatches = listOf(),
                    activeMode = Mode.Monochrome,
                    selectedMode = Mode.Monochrome,
                    onModeSelect = {},
                    activeSwatchCount = SwatchCount.Six,
                    selectedSwatchCount = SwatchCount.Six,
                    onSwatchCountSelect = onSwatchCountSelectSlot.captured,
                    applyChangesButton = ApplyChangesButton.Hidden,
                )
            }
            createSut()
            sut.getColorScheme(seed = mockk()) // from other tests we know that state will become Ready after this call

            sut.data.onSwatchCountSelect(SwatchCount.Thirteen)

            sut.data.applyChangesButton should beOfType<ApplyChangesButton.Visible>()
        }

    @Test
    fun `selecting new swatch count that is same as the active swatch count makes 'apply changes' button hidden`() =
        runTest(mainDispatcherRule.testDispatcher) {
            every { getInitialState() } returns State.Loading
            coEvery { getColorScheme(request = any<GetColorSchemeUseCase.Request>()) } returns mockk()
            val onSwatchCountSelectSlot = slot<(SwatchCount) -> Unit>()
            every {
                colorSchemeDataFactory.create(
                    scheme = any(),
                    config = any(),
                    onModeSelect = any(),
                    onSwatchCountSelect = capture(onSwatchCountSelectSlot),
                )
            } answers {
                ColorSchemeData(
                    swatches = listOf(),
                    activeMode = Mode.Monochrome,
                    selectedMode = Mode.Monochrome,
                    onModeSelect = {},
                    activeSwatchCount = SwatchCount.Six,
                    selectedSwatchCount = SwatchCount.Thirteen,
                    onSwatchCountSelect = onSwatchCountSelectSlot.captured,
                    applyChangesButton = ApplyChangesButton.Hidden,
                )
            }
            createSut()
            sut.getColorScheme(seed = mockk()) // from other tests we know that state will become Ready after this call

            sut.data.onSwatchCountSelect(SwatchCount.Six)

            sut.data.applyChangesButton should beOfType<ApplyChangesButton.Hidden>()
        }

    @Test
    fun `clicking 'apply changes' applies selected values to their actual conterparts`() =
        runTest(mainDispatcherRule.testDispatcher) {

        }

    fun createSut() =
        ColorSchemeViewModel(
            getInitialState = getInitialState,
            getColorScheme = getColorScheme,
            colorSchemeDataFactory = colorSchemeDataFactory,
            ioDispatcher = mainDispatcherRule.testDispatcher,
        ).also {
            sut = it
        }
}