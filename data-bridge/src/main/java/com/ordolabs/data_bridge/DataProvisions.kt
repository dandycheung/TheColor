package com.ordolabs.data_bridge

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.ordolabs.domain.di.DomainDependencies

interface DataProvisions : DomainDependencies {

    val preferences: DataStore<Preferences>
}