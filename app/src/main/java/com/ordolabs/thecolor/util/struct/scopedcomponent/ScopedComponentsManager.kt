package com.ordolabs.thecolor.util.struct.scopedcomponent

import androidx.lifecycle.Lifecycle

/**
 * Manager for scoped component stores.
 * [ScopedComponentStore] will be removed from [stores], once its [Disposable.dispose] method is called.
 */
class ScopedComponentsManager : Disposable {

    val stores = mutableSetOf<ScopedComponentStore<*>>()

    inline fun <reified C> storeOf(): ScopedComponentStore<*>? {
        stores.forEach {
            if (it.component is C) return it
        }
        return null
    }

    inline fun <reified C> componentOf(): C? {
        val store = stores.firstOrNull { it.component is C } ?: return null
        return store.component as C
    }

    fun <C> add(component: C, lifecycle: Lifecycle): ScopedComponentStore<C> {
        val listener = makeOnDisposeListener()
        val store = ScopedComponentStore(component, lifecycle, listener)
        stores.add(store)
        return store
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