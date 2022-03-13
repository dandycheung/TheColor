package com.ordolabs.feature_settings.di.module

import com.ordolabs.thecolor.di.module.AppViewModelModule
import dagger.Module

@Module(
    includes = [AppViewModelModule::class]
)
interface FeatureSettingsViewModelModule