package com.ordolabs.thecolor.util.struct.scopedcomponent

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner

/**
 * Stores [component] of type [C], while there are subscribers using it.
 */
class ScopedComponentStore<C>(
    component: C,
    subscriber: Lifecycle,
    private val l: OnDisposeListener? = null
) : Disposable {

    val component: C
        get() = requireNotNull(_component)

    private var _component: C? = component
    private val subscribers: MutableSet<Subscriber> = mutableSetOf()

    init {
        subscribe(subscriber)
    }

    /**
     * Subscribes specified [lifecycle] for [component] usage.
     * Once [lifecycle] reaches [Lifecycle.State.DESTROYED] state, it will be automatically [unsubscribe]d.
     */
    fun subscribe(lifecycle: Lifecycle): Boolean {
        val isNew = (getSubscriberWithLifecycle(lifecycle) == null)
        if (isNew) {
            val observer = makeLifecycleObserver()
            val subscriber = Subscriber(lifecycle, observer)
            lifecycle.addObserver(makeLifecycleObserver())
            subscribers.add(subscriber)
        }
        return isNew
    }

    /**
     * Unsubscribes specified [lifecycle] from [component] usage.
     * Once there is no more subscribers, [dispose] will be called.
     */
    fun unsubscribe(lifecycle: Lifecycle): Boolean {
        val subscriber = getSubscriberWithLifecycle(lifecycle) ?: return false
        subscriber.lifecycle.removeObserver(subscriber.observer)
        subscribers.remove(subscriber)
        if (subscribers.isEmpty()) {
            dispose()
        }
        return true
    }

    // region Disposable

    override fun dispose() {
        this._component = null
        l?.onDisposed(this)
    }

    // endregion

    private fun getSubscriberWithLifecycle(lifecycle: Lifecycle) =
        subscribers.firstOrNull { it.lifecycle == lifecycle }

    /**
     * @see subscribe
     */
    private fun makeLifecycleObserver() =
        object : DefaultLifecycleObserver {

            override fun onDestroy(owner: LifecycleOwner) {
                unsubscribe(owner.lifecycle)
            }
        }

    fun interface OnDisposeListener {
        fun onDisposed(store: ScopedComponentStore<*>)
    }

    private data class Subscriber(
        val lifecycle: Lifecycle,
        val observer: LifecycleObserver
    )
}