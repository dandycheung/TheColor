package com.ordolabs.data_bridge.module

import com.ordolabs.data_bridge.module.local.DataLocalModule
import com.ordolabs.data_bridge.module.remote.DataRemoteModule
import com.ordolabs.data_bridge.module.repository.DataRepositoryModule
import dagger.Module

@Module(
    includes = [
        DataLocalModule::class,
        DataRemoteModule::class,
        DataRepositoryModule::class
    ]
)
interface DataModule