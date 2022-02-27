package com.ordolabs.feature_settings.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnPreDraw
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.ordolabs.feature_settings.R
import com.ordolabs.feature_settings.databinding.SettingsFragmentBinding
import com.ordolabs.feature_settings.model.Category
import com.ordolabs.feature_settings.ui.adapter.CategoryAdapter
import com.ordolabs.thecolor.ui.adapter.base.OnRecyclerItemClicksListener
import com.ordolabs.thecolor.util.ext.setActivitySupportActionBar
import com.ordolabs.thecolor.R as RApp

class SettingsFragment :
    BaseFragment(),
    OnRecyclerItemClicksListener {

    private val binding: SettingsFragmentBinding by viewBinding()

    private val categoryAdapter =
        CategoryAdapter(makeCategories()).also {
            it.setOnClicksListener(this)
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.settings_fragment, container, false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        postponeEnterTransition()
    }

    // region Set views

    override fun setViews() {
        setToolbar()
        setCategoryRecycler()
    }

    private fun setToolbar() =
        binding.run {
            setActivitySupportActionBar(toolbar)
            toolbar.setNavigationOnClickListener(::onToolbarNavigationClick)
        }

    private fun setCategoryRecycler() {
        val recycler = binding.categoryRecycler
        recycler.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        recycler.adapter = categoryAdapter
        recycler.doOnPreDraw {
            startPostponedEnterTransition()
        }
    }

    // endregion

    // region View actions

    @Suppress("UNUSED_PARAMETER")
    private fun onToolbarNavigationClick(view: View) {
        findNavController().navigateUp()
    }

    // endregion

    // region OnRecyclerItemClicksListener

    override fun onRecyclerItemClick(position: Int) {
        val category = categoryAdapter.categories.getOrNull(position) ?: return
        val view = binding.categoryRecycler.layoutManager?.findViewByPosition(position) ?: return
        navigateToCategory(view, category)
    }

    // endregion

    private fun navigateToCategory(itemView: View, category: Category) {
        val transitionName = itemView.transitionName
        val action = SettingsFragmentDirections.actionToCategory(category, transitionName)
        val extras = FragmentNavigatorExtras(
            itemView to transitionName
        )
        findNavController().navigate(action, extras)
    }

    private fun makeCategories() =
        arrayListOf(
            Category(
                contentType = Category.ContentType.APPEARANCE,
                titleRes = R.string.settings_category_appearance,
                iconRes = RApp.drawable.ic_palette
            ),
            Category(
                contentType = Category.ContentType.APPEARANCE,
                titleRes = R.string.settings_category_appearance,
                iconRes = RApp.drawable.ic_palette
            ),
            Category(
                contentType = Category.ContentType.APPEARANCE,
                titleRes = R.string.settings_category_appearance,
                iconRes = RApp.drawable.ic_palette
            ),
            Category(
                contentType = Category.ContentType.APPEARANCE,
                titleRes = R.string.settings_category_appearance,
                iconRes = RApp.drawable.ic_palette
            ),
            Category(
                contentType = Category.ContentType.APPEARANCE,
                titleRes = R.string.settings_category_appearance,
                iconRes = RApp.drawable.ic_palette
            ),
            Category(
                contentType = Category.ContentType.APPEARANCE,
                titleRes = R.string.settings_category_appearance,
                iconRes = RApp.drawable.ic_palette
            ),
            Category(
                contentType = Category.ContentType.APPEARANCE,
                titleRes = R.string.settings_category_appearance,
                iconRes = RApp.drawable.ic_palette
            ),
            Category(
                contentType = Category.ContentType.APPEARANCE,
                titleRes = R.string.settings_category_appearance,
                iconRes = RApp.drawable.ic_palette
            ),
            Category(
                contentType = Category.ContentType.APPEARANCE,
                titleRes = R.string.settings_category_appearance,
                iconRes = RApp.drawable.ic_palette
            ),
            Category(
                contentType = Category.ContentType.APPEARANCE,
                titleRes = R.string.settings_category_appearance,
                iconRes = RApp.drawable.ic_palette
            ),
            Category(
                contentType = Category.ContentType.APPEARANCE,
                titleRes = R.string.settings_category_appearance,
                iconRes = RApp.drawable.ic_palette
            ),
            Category(
                contentType = Category.ContentType.APPEARANCE,
                titleRes = R.string.settings_category_appearance,
                iconRes = RApp.drawable.ic_palette
            ),
            Category(
                contentType = Category.ContentType.APPEARANCE,
                titleRes = R.string.settings_category_appearance,
                iconRes = RApp.drawable.ic_palette
            ),
            Category(
                contentType = Category.ContentType.APPEARANCE,
                titleRes = R.string.settings_category_appearance,
                iconRes = RApp.drawable.ic_palette
            ),
            Category(
                contentType = Category.ContentType.APPEARANCE,
                titleRes = R.string.settings_category_appearance,
                iconRes = RApp.drawable.ic_palette
            ),
            Category(
                contentType = Category.ContentType.APPEARANCE,
                titleRes = R.string.settings_category_appearance,
                iconRes = RApp.drawable.ic_palette
            ),
            Category(
                contentType = Category.ContentType.APPEARANCE,
                titleRes = R.string.settings_category_appearance,
                iconRes = RApp.drawable.ic_palette
            ),
            Category(
                contentType = Category.ContentType.APPEARANCE,
                titleRes = R.string.settings_category_appearance,
                iconRes = RApp.drawable.ic_palette
            )
        )

    companion object {
        // being created by NavHostFragment, thus no newInstance() method
    }
}