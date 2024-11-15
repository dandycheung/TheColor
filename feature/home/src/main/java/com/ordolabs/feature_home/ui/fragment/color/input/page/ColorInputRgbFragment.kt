package com.ordolabs.feature_home.ui.fragment.color.input.page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import by.kirich1409.viewbindingdelegate.viewBinding
import com.ordolabs.feature_home.R
import com.ordolabs.feature_home.databinding.ColorInputRgbFragmentBinding
import io.github.mmolosay.presentation.model.color.ColorPrototype
import io.github.mmolosay.presentation.ui.util.inputfilter.PreventingInputFilter
import io.github.mmolosay.presentation.ui.util.inputfilter.RangeInputFilter
import io.github.mmolosay.presentation.util.ext.addFilters
import io.github.mmolosay.presentation.util.ext.getText
import io.github.mmolosay.presentation.util.ext.getTextString
import io.github.mmolosay.presentation.util.ext.setTextPreservingSelection
import io.github.mmolosay.presentation.util.struct.Resource
import kotlinx.coroutines.flow.Flow

class ColorInputRgbFragment :
    BaseColorInputFragment<ColorPrototype.Rgb>() {

    private val binding by viewBinding(ColorInputRgbFragmentBinding::bind)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.color_input_rgb_fragment, container, false)
    }

    // region Set views

    override fun setViews() {
        setComponentInputsFilters()
        setComponentRTextWatcher()
        setComponentGTextWatcher()
        setComponentBTextWatcher()
    }

    private fun setComponentInputsFilters() = binding.run {
        val range = RangeInputFilter(min = 0, max = 255)
        val preventing = PreventingInputFilter(preceding = "0", what = "0")
        inputRgbR.editText?.addFilters(range, preventing)
        inputRgbG.editText?.addFilters(range, preventing)
        inputRgbB.editText?.addFilters(range, preventing)
    }

    private fun setComponentRTextWatcher() =
        binding.inputRgbR.editText?.doOnTextChanged { _, _, _, _ ->
            outputOnInputChanges()
        }

    private fun setComponentGTextWatcher() =
        binding.inputRgbG.editText?.doOnTextChanged { _, _, _, _ ->
            outputOnInputChanges()
        }

    private fun setComponentBTextWatcher() =
        binding.inputRgbB.editText?.doOnTextChanged { _, _, _, _ ->
            outputOnInputChanges()
        }

    // endregion

    // region BaseColorInputFragment

    override fun assemblePrototype(): ColorPrototype.Rgb {
        val r = binding.inputRgbR.getTextString()?.toIntOrNull()
        val g = binding.inputRgbG.getTextString()?.toIntOrNull()
        val b = binding.inputRgbB.getTextString()?.toIntOrNull()
        return ColorPrototype.Rgb(r, g, b)
    }

    override fun populateViews(color: ColorPrototype.Rgb): Unit =
        binding.run {
            inputRgbR.editText?.setTextPreservingSelection(color.r.toString())
            inputRgbG.editText?.setTextPreservingSelection(color.g.toString())
            inputRgbB.editText?.setTextPreservingSelection(color.b.toString())
        }

    override fun clearViews(): Unit =
        binding.run {
            binding.inputRgbR.getText()?.clear()
            binding.inputRgbG.getText()?.clear()
            binding.inputRgbB.getText()?.clear()
        }

    override fun getColorInputFlow(): Flow<Resource<ColorPrototype.Rgb>> =
        colorInputVM.inputRgb

    // endregion

    companion object {

        fun newInstance() =
            ColorInputRgbFragment()
    }
}