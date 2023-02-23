package com.example.core.base.presentation

import androidx.fragment.app.Fragment

open class BaseFragment : Fragment() {
    fun hasToolbar(show: Boolean) {
        (activity as? BaseActivity)?.hasToolbar(show)
    }

    override fun onDestroy() {
        super.onDestroy()
        hasToolbar(false)
    }
}