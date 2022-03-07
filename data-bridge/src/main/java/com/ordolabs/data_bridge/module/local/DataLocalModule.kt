package com.ordolabs.data_bridge.module.local

import dagger.Module

@Module(
    includes = [
        DataLocalDatabaseModule::class,
        DataLocalPreferencesModule::class
    ]
)
interface DataLocalModule