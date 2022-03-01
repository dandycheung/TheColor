package com.ordolabs.feature_settings.di

import androidx.lifecycle.ViewModelProvider
import com.ordolabs.feature_settings.di.module.FeatureSettingsModule
import com.ordolabs.thecolor.di.AppComponent
import com.ordolabs.thecolor.di.scope.FeatureScope
import dagger.Component

@FeatureScope
@Component(
    modules = [FeatureSettingsModule::class],
    dependencies = [AppComponent::class]
)
interface FeatureSettingsComponent {

    // region Provisions

    // provisions are declared in-place (not in interface),
    // because feature components are final and should not be inherited

    val viewModelFactory: ViewModelProvider.Factory
//    val savedStateViewModelFactoryFactory: SavedStateViewModelFactoryFactory

    // endregion

    // region Injections

    // endregion

    @Component.Builder
    interface Builder {
        fun appComponent(appComponent: AppComponent): Builder
        fun build(): FeatureSettingsComponent
    }
}