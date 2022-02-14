package com.isranascimento.core.fragment

import android.os.Bundle
import android.view.View
import androidx.annotation.CallSuper
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.isranascimento.core.R

abstract class BaseToolbarFragment: Fragment() {
    abstract fun getToolbarTitle(): String

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.findViewById<Toolbar?>(R.id.toolbar)?.apply {
            this.title = getToolbarTitle()
        }
    }
}