package com.ordolabs.thecolor.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ordolabs.thecolor.di.mapkey.ViewModelKey
import com.ordolabs.thecolor.viewmodel.SettingsViewModel
import com.ordolabs.thecolor.viewmodel.factory.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Module to be included in feature modules.
 */
@Module
interface AppViewModelModule {

    @Binds
    fun bindViewModelFactory(instance: ViewModelFactory): ViewModelProvider.Factory

    @[Binds IntoMap ViewModelKey(SettingsViewModel::class)]
    fun bindSettingsViewModel(vm: SettingsViewModel): ViewModel
}