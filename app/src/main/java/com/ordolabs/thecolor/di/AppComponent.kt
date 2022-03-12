package com.ordolabs.thecolor.di

import androidx.lifecycle.ViewModelProvider
import com.ordolabs.domain.di.DomainComponent
import com.ordolabs.thecolor.di.module.AppModule
import com.ordolabs.thecolor.di.scope.AppScope
import dagger.Component

@AppScope
@Component(
    modules = [AppModule::class],
    dependencies = [DomainComponent::class]
)
interface AppComponent : AppProvisions {

    // region Provisions

    val viewModelFactory: ViewModelProvider.Factory

    // endregion

    @Component.Builder
    interface Builder {
        fun domainComponent(instance: DomainComponent): Builder
        fun build(): AppComponent
    }
}