package com.ordolabs.feature_home.di

import androidx.lifecycle.ViewModelProvider
import com.ordolabs.feature_home.di.module.FeatureHomeModule
import com.ordolabs.thecolor.di.AppProvisions
import com.ordolabs.thecolor.di.scope.FeatureScope
import com.ordolabs.thecolor.viewmodel.factory.SavedStateViewModelFactoryFactory
import dagger.Component

@FeatureScope
@Component(
    modules = [FeatureHomeModule::class],
    dependencies = [AppProvisions::class]
)
interface FeatureHomeComponent {

    // region Provisions

    val viewModelFactory: ViewModelProvider.Factory
    val savedStateViewModelFactoryFactory: SavedStateViewModelFactoryFactory

    // endregion

    // region Injections

    // endregion

    @Component.Builder
    interface Builder {
        fun appProvisions(instance: AppProvisions): Builder
        fun build(): FeatureHomeComponent
    }
}