package com.ordolabs.feature_home.ui.fragment.color.data.base

import io.github.mmolosay.presentation.fragment.BaseFragment

/**
 * Fragment that displays color data of type [D] and nothing more.
 * Has no functionality of independently obtaining data from outer source.
 */
abstract class BaseColorDataFragment<D> :
    BaseFragment(),
    ColorDataView<D>