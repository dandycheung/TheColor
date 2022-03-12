package com.ordolabs.thecolor.util.struct.scopedcomponent

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner

/**
 * Stores [component] of type [C] withing specified lifecycle.
 * [component] will be released automatically when lifecycle reaches [Lifecycle.State.DESTROYED] state.
 */
class ScopedComponentStore<C>(
    component: C,
    lifecycle: Lifecycle,
    private val l: OnDisposeListener? = null
) : Disposable {

    val component: C
        get() = requireNotNull(_component)

    private var _component: C? = component

    init {
        lifecycle.addObserver(makeLifecycleObserver())
    }

    // region Disposable

    override fun dispose() {
        this._component = null
        l?.onDisposed(this)
    }

    // endregion

    private fun makeLifecycleObserver() =
        object : DefaultLifecycleObserver {

            override fun onDestroy(owner: LifecycleOwner) {
                dispose()
            }
        }

    fun interface OnDisposeListener {
        fun onDisposed(store: ScopedComponentStore<*>)
    }
}