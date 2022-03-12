package com.ordolabs.thecolor.di

import androidx.lifecycle.ViewModel
import com.ordolabs.domain.di.DomainComponent
import com.ordolabs.domain.di.DomainProvisions
import com.ordolabs.thecolor.di.module.AppModule
import com.ordolabs.thecolor.di.scope.AppScope
import dagger.Component

@AppScope
@Component(
    modules = [AppModule::class],
    dependencies = [DomainComponent::class]
)
interface AppComponent : DomainProvisions {

    // region Provisions

    val viewModelMultibinding: Map<Class<out ViewModel>, @JvmSuppressWildcards ViewModel>

    // endregion

    @Component.Builder
    interface Builder {
        fun domainComponent(instance: DomainComponent): Builder
        fun build(): AppComponent
    }
}