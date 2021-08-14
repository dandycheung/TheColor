package com.ordolabs.thecolor.util

import androidx.appcompat.app.AppCompatActivity
import com.github.michaelbull.result.Result
import com.ordolabs.thecolor.R
import com.ordolabs.thecolor.ui.fragment.BaseFragment

fun AppCompatActivity.setFragment(fragment: BaseFragment): Result<Int, Throwable> {
    return ContextUtil.setFragment(
        this.supportFragmentManager,
        fragment,
        R.id.defaultFragmentContainer,
        fragment.transactionTag
    )
}

fun AppCompatActivity.replaceFragment(fragment: BaseFragment): Result<Int, Throwable> {
    return ContextUtil.replaceFragment(
        this.supportFragmentManager,
        fragment,
        R.id.defaultFragmentContainer,
        fragment.transactionTag
    )
}

