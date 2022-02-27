package com.ordolabs.thecolor.ui.activity

import by.kirich1409.viewbindingdelegate.viewBinding
import com.ordolabs.thecolor.R
import com.ordolabs.thecolor.databinding.MainActivityBinding

class MainActivity : BaseActivity(R.layout.main_activity) {

    private val binding: MainActivityBinding by viewBinding()

    override fun setViews() {
        // nothing is here
    }
}