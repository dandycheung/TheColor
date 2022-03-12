package com.ordolabs.thecolor.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

/*
 * due to it receives 'providers' in constructor, same ViewModelFactory instance can't be shared
 * between feature components — they bind their ViewModel-s later, after ViewModelFactory was already
 * created, and 'providers' contains only ViewModels from the same component, where ViewModelFactory
 * was instantiated.
 */
class ViewModelFactory @Inject constructor(
    private val providers: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val key = modelClass as Class<ViewModel>
        val provider = providers.getValue(key)
        return provider.get() as T
    }
}