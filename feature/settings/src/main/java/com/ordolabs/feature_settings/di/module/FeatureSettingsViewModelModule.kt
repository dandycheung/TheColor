package com.ordolabs.feature_settings.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ordolabs.feature_settings.viewmodel.AppearanceViewModel
import com.ordolabs.thecolor.di.mapkey.ViewModelKey
import com.ordolabs.thecolor.viewmodel.factory.ViewModelFactory
import dagger.Binds
import dagger.multibindings.IntoMap

interface FeatureSettingsViewModelModule {

    @Binds
    fun bindViewModelFactory(instance: ViewModelFactory): ViewModelProvider.Factory

    @[Binds IntoMap ViewModelKey(AppearanceViewModel::class)]
    fun bindAppearanceViewModel(vm: AppearanceViewModel): ViewModel
}