package com.ordolabs.feature_settings.di.module

import dagger.Module

@Module(
    includes = [
        FeatureSettingsViewModelModule::class
    ]
)
interface FeatureSettingsModule