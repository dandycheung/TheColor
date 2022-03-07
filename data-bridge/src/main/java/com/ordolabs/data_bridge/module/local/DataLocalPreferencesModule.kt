package com.ordolabs.data_bridge.module.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import dagger.Module
import dagger.Provides

@Module
class DataLocalPreferencesModule {

    @Provides
    fun provideDataStorePreferences(
        context: Context
    ): DataStore<Preferences> =
        PreferenceDataStoreFactory.create {
            context.preferencesDataStoreFile(PREFERENCES_NAME)
        }

    companion object {
        private const val PREFERENCES_NAME = "com.ordolabs.thecolor.datastore_preferences"
    }
}