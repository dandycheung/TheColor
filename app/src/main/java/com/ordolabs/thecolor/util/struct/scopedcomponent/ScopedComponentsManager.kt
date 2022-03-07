package com.ordolabs.thecolor.util.struct.scopedcomponent

import androidx.lifecycle.Lifecycle

/**
 * Manager for scoped component stores.
 * [ScopedComponentStore] will be removed from [stores], once its [Disposable.dispose] method is called.
 */
class ScopedComponentsManager : Disposable {

    val stores = mutableSetOf<ScopedComponentStore<*>>()

    inline fun <reified C> componentOf(): C {
        val store = stores.first { it.component is C }
        return store.component as C
    }

    fun <C> add(component: C, lifecycle: Lifecycle): ScopedComponentStore<C> =
        ScopedComponentStore(component, lifecycle, makeOnDisposeListener()).also { store ->
            stores.add(store)
        }

    // region Disposable

    override fun dispose() {
        stores.forEach { it.dispose() }
        stores.clear()
    }

    // endregion

    private fun makeOnDisposeListener() =
        ScopedComponentStore.OnDisposeListener { store ->
            stores.remove(store)
        }
}