package com.ordolabs.thecolor.ui.activity

import by.kirich1409.viewbindingdelegate.viewBinding
import com.ordolabs.thecolor.R
import com.ordolabs.thecolor.databinding.MainActivityBinding

class MainActivity : BaseActivity() {

    private val binding: MainActivityBinding by viewBinding()

    override fun setViews() {
        setContentView(R.layout.main_activity)
    }
}