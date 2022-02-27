package com.ordolabs.feature_settings.ui.adapter

import android.view.View
import com.ordolabs.feature_settings.R
import com.ordolabs.feature_settings.databinding.SettingsCategoryItemBinding
import com.ordolabs.feature_settings.model.Category
import com.ordolabs.thecolor.ui.adapter.base.BaseAdapter
import com.ordolabs.thecolor.ui.adapter.base.BaseViewHolder

class CategoryAdapter(
    val categories: ArrayList<Category>
) : BaseAdapter<Category, CategoryViewHolder>() {

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = categories.getOrNull(position) ?: return
        holder.populate(category)
    }

    override fun setItems(items: List<Category>) {
        require(categories.isEmpty()) { "categories should not be changing once set" }
        val oldSize = categories.size
        val newSize = items.size
        categories.clear()
        categories.addAll(items)
        notifyItemRangeChanged(0, maxOf(oldSize, newSize))
    }

    override fun createViewHolder(itemView: View) =
        CategoryViewHolder(itemView)

    override fun getItemViewLayout(viewType: Int): Int =
        R.layout.settings_category_item

    override fun getItemCount(): Int =
        categories.size
}

class CategoryViewHolder(itemView: View) : BaseViewHolder<Category>(itemView) {

    override fun populate(item: Category): Unit =
        getItemViewBinding().run {
            category.setCompoundDrawablesWithIntrinsicBounds(item.iconRes, 0, 0, 0)
            category.setText(item.titleRes)
        }

    private fun getItemViewBinding(): SettingsCategoryItemBinding =
        SettingsCategoryItemBinding.bind(itemView)
}