package com.ordolabs.thecolor.di.module

import androidx.lifecycle.ViewModel
import dagger.Module
import dagger.multibindings.Multibinds

@Module(
    includes = [
        AppViewModelModule::class
    ]
)
interface AppModule {

    @Multibinds
    fun multibindViewModels(): Map<Class<out ViewModel>, @JvmSuppressWildcards ViewModel>
}