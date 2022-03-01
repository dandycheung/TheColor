package com.ordolabs.feature_settings.di

import com.ordolabs.thecolor.di.ScopedComponentKeeper

/**
 * Interface of object, keeping instance of [FeatureSettingsComponent].
 *
 * @see ScopedComponentKeeper
 */
interface FeatureSettingsComponentKeeper : ScopedComponentKeeper {
    val featureSettingsComponent: FeatureSettingsComponent
}