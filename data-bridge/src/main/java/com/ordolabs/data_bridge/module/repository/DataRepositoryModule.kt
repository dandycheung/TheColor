package com.ordolabs.data_bridge.module.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.ordolabs.data.repository.color.ColorRemoteRepositoryImpl
import com.ordolabs.data.repository.color.ColorValidatorRepositoryImpl
import com.ordolabs.data.repository.color.ColorsHistoryRepositoryImpl
import com.ordolabs.data.repository.settings.ApplicationSettingsRepositoryImpl
import com.ordolabs.data_local.database.dao.ColorsHistoryDao
import com.ordolabs.data_remote.api.TheColorApiService
import com.ordolabs.domain.repository.color.ColorRemoteRepository
import com.ordolabs.domain.repository.color.ColorValidatorRepository
import com.ordolabs.domain.repository.color.ColorsHistoryRepository
import com.ordolabs.domain.repository.settings.ApplicationSettingsRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataRepositoryModule {

    // region Color

    @Provides
    @Singleton
    fun provideColorValidatorRepository(): ColorValidatorRepository =
        ColorValidatorRepositoryImpl()

    @Provides
    @Singleton
    fun provideColorsHistoryRepository(
        dao: ColorsHistoryDao
    ): ColorsHistoryRepository =
        ColorsHistoryRepositoryImpl(dao)

    @Provides
    @Singleton
    fun provideColorRemoteRepository(
        api: TheColorApiService
    ): ColorRemoteRepository =
        ColorRemoteRepositoryImpl(api)

    // endregion

    // region Settings

    @Provides
    @Singleton
    fun provideApplicationSettingsRepostiroy(
        datastore: DataStore<Preferences>
    ): ApplicationSettingsRepository =
        ApplicationSettingsRepositoryImpl(datastore)

    // endregion
}