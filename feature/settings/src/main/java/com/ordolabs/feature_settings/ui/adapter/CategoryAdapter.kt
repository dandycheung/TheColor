package com.ordolabs.feature_settings.ui.adapter

import android.view.View
import com.ordolabs.feature_settings.R
import com.ordolabs.feature_settings.databinding.SettingsCategoryItemBinding
import com.ordolabs.thecolor.model.settings.Category
import com.ordolabs.thecolor.ui.adapter.base.BaseAdapter
import com.ordolabs.thecolor.ui.adapter.base.BaseViewHolder

class CategoryAdapter(
    val categories: ArrayList<Category>
) : BaseAdapter<Category, CategoryViewHolder>() {

    fun getItemViewTransitionName(position: Int): String =
        ITEM_VIEW_TRANSITION_NAME + position

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

    companion object {
        private const val ITEM_VIEW_TRANSITION_NAME = "title"
    }
}

class CategoryViewHolder(itemView: View) : BaseViewHolder<Category>(itemView) {

    override fun populate(item: Category): Unit =
        getItemViewBinding().run {
            val transitionName = getCategoryAdapter()?.getItemViewTransitionName(layoutPosition)
            category.setCompoundDrawablesWithIntrinsicBounds(item.iconRes, 0, 0, 0)
            category.setText(item.titleRes)
            category.transitionName = transitionName
        }

    private fun getCategoryAdapter() =
        bindingAdapter as? CategoryAdapter

    private fun getItemViewBinding(): SettingsCategoryItemBinding =
        SettingsCategoryItemBinding.bind(itemView)
}