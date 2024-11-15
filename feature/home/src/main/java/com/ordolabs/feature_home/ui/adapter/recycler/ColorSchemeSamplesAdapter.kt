package com.ordolabs.feature_home.ui.adapter.recycler

import android.view.View
import com.ordolabs.feature_home.R
import com.ordolabs.feature_home.databinding.ColorSchemeSampleItemBinding
import io.github.mmolosay.presentation.model.color.data.ColorScheme
import io.github.mmolosay.presentation.model.color.toColorInt
import io.github.mmolosay.presentation.ui.adapter.base.BaseAdapter
import io.github.mmolosay.presentation.ui.adapter.base.BaseViewHolder

class ColorSchemeSamplesAdapter : BaseAdapter<ColorScheme.Sample, ColorSchemeSampleViewHolder>() {

    val samples: ArrayList<ColorScheme.Sample> = arrayListOf()

    override fun onBindViewHolder(holder: ColorSchemeSampleViewHolder, position: Int) {
        ensureIndexInItemsRange(position) ?: return
        val sample = samples[position]
        holder.populate(sample)
    }

    override fun setItems(items: List<ColorScheme.Sample>) {
        val oldSize = samples.size
        val newSize = items.size
        samples.clear()
        samples.addAll(items)
        // notifyItemRangeChanged(0, items.size) may cause an exception (bug) in RecyclerView
        notifyItemRangeRemoved(0, oldSize)
        notifyItemRangeInserted(0, newSize)
    }

    override fun createViewHolder(itemView: View) =
        ColorSchemeSampleViewHolder(itemView)

    override fun getItemViewLayout(viewType: Int): Int =
        R.layout.color_scheme_sample_item

    override fun getItemCount(): Int =
        samples.count()
}

class ColorSchemeSampleViewHolder(itemView: View) : BaseViewHolder<ColorScheme.Sample>(itemView) {

    override fun populate(item: ColorScheme.Sample): Unit =
        getItemViewBinding().run {
            val color = item.color.toColorInt()
            sample.setCardBackgroundColor(color)
        }

    private fun getItemViewBinding(): ColorSchemeSampleItemBinding =
        ColorSchemeSampleItemBinding.bind(itemView)
}