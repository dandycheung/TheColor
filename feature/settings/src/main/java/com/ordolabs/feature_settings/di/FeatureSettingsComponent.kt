package com.ordolabs.feature_settings.di

import androidx.lifecycle.ViewModelProvider
import com.ordolabs.feature_settings.di.module.FeatureSettingsModule
import com.ordolabs.thecolor.di.AppProvisions
import com.ordolabs.thecolor.di.scope.FeatureScope
import dagger.Component

@FeatureScope
@Component(
    modules = [FeatureSettingsModule::class],
    dependencies = [AppProvisions::class]
)
interface FeatureSettingsComponent {

    // region Provisions

    val viewModelFactory: ViewModelProvider.Factory
//    val savedStateViewModelFactoryFactory: SavedStateViewModelFactoryFactory

    // endregion

    // region Injections

    // endregion

    @Component.Builder
    interface Builder {
        fun appProvisions(instance: AppProvisions): Builder
        fun build(): FeatureSettingsComponent
    }
}