package com.ordolabs.data_bridge

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.ordolabs.data_bridge.module.DataModule
import com.ordolabs.domain.di.DomainDependencies
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DataModule::class])
interface DataComponent : DomainDependencies {

    // region Provisions

    val preferences: DataStore<Preferences>

    // endregion

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun applicationContext(instance: Context): Builder
        fun build(): DataComponent
    }
}