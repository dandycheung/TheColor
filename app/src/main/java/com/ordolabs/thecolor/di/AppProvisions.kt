package com.ordolabs.thecolor.di

import androidx.lifecycle.ViewModel
import com.ordolabs.domain.di.DomainProvisions

// TODO: get rid of
interface AppProvisions :
    DomainProvisions {

    val viewModelMultibinding: Map<Class<out ViewModel>, @JvmSuppressWildcards ViewModel>
}