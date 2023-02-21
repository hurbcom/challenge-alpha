package com.example.core.base

import androidx.fragment.app.Fragment

open class BaseFragment : Fragment() {
    fun hasToolbar(show: Boolean) {
        (activity as? BaseActivity)?.hasToolbar(show)
    }
}