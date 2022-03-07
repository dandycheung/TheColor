package com.ordolabs.thecolor

import android.app.Application
import com.ordolabs.data_bridge.DaggerDataComponent
import com.ordolabs.domain.di.DaggerDomainComponent
import com.ordolabs.thecolor.di.AppComponent
import com.ordolabs.thecolor.di.DaggerAppComponent
import com.ordolabs.thecolor.util.struct.scopedcomponent.ScopedComponentsManager

internal class TheColorApplication : Application() {

    lateinit var appComponent: AppComponent
    val scopedComponentsManager = ScopedComponentsManager()

    override fun onCreate() {
        super.onCreate()
        setDi()
    }

    // region DI

    private fun setDi() {
        val dataComponent = DaggerDataComponent
            .builder()
            .applicationContext(this)
            .build()
        val domainComponent = DaggerDomainComponent
            .builder()
            .dependencies(dataComponent)
            .build()
        this.appComponent = DaggerAppComponent
            .builder()
            .domainComponent(domainComponent)
            .build()
    }

    // endregion
}