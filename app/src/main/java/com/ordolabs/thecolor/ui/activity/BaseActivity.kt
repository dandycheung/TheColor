package com.ordolabs.thecolor.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseStartIntent()
        setUp()
        setFragments()
        setViews()
    }

    // region onCreate

    /**
     * Parses `Intent`, that started this `Activity`.
     */
    protected open fun parseStartIntent() {
        // default empty implementation
    }

    /**
     * Configures non-view components.
     * Being called in [onCreate] method.
     */
    protected open fun setUp() {
        // default empty implementation
    }

    /**
     * Configures child fragments.
     * Being called in [onCreate] method.
     */
    protected open fun setFragments() {
        // default empty implementation
    }

    /**
     * Sets `Activity`'s views and configures them.
     * Being called in [onCreate] method.
     */
    protected abstract fun setViews()

    // endregion

    companion object {
        // extra keys and stuff
    }
}