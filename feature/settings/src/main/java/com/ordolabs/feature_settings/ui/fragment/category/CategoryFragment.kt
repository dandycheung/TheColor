package com.ordolabs.feature_settings.ui.fragment.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import by.kirich1409.viewbindingdelegate.viewBinding
import com.ordolabs.feature_settings.R
import com.ordolabs.feature_settings.databinding.CategoryFragmentBinding
import com.ordolabs.feature_settings.model.Category
import com.ordolabs.feature_settings.ui.fragment.BaseFragment
import com.ordolabs.feature_settings.ui.fragment.category.content.AboutCategoryContentFragment
import com.ordolabs.feature_settings.ui.fragment.category.content.AppearanceCategoryContentFragment
import com.ordolabs.thecolor.util.ext.setActivitySupportActionBar
import com.ordolabs.thecolor.util.ext.setFragmentOrGet

class CategoryFragment : BaseFragment() {

    private val binding: CategoryFragmentBinding by viewBinding()
    private val args: CategoryFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.category_fragment, container, false)
    }

    // region Set up

    override fun setUp() {
        setSharedElementTransitions()
    }

    private fun setSharedElementTransitions() {
        val context = context ?: return
        val transition = TransitionInflater
            .from(context)
            .inflateTransition(android.R.transition.move)
        this.sharedElementEnterTransition = transition
        this.sharedElementReturnTransition = transition
    }

    // endregion

    // region Set fragments

    override fun setFragments() {
        setContentFragment()
    }

    private fun setContentFragment() {
        val container = binding.contentFragmentContainer
        val type = args.category.contentType
        setFragmentOrGet(container.id) {
            createFragmentFromContentType(type)
        }
    }

    // endregion

    // region Set views

    override fun setViews() {
        setToolbar()
    }

    private fun setToolbar() =
        binding.run {
            setActivitySupportActionBar(toolbar)
            toolbar.setTitle(args.category.titleRes)
            toolbar.setNavigationOnClickListener(::onToolbarNavigationClick)
            toolbar.transitionName = args.transitionName
        }

    // endregion

    // region View actions

    @Suppress("UNUSED_PARAMETER")
    private fun onToolbarNavigationClick(view: View) {
        findNavController().navigateUp()
    }

    // endregion

    private fun createFragmentFromContentType(type: Category.ContentType) =
        when (type) {
            Category.ContentType.APPEARANCE -> AppearanceCategoryContentFragment.newInstance()
            Category.ContentType.ABOUT -> AboutCategoryContentFragment.newInstance()
        }

    companion object {
        // being created by NavHostFragment, thus no newInstance() method
    }
}