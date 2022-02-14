package com.isranascimento.core.fragment

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.annotation.CallSuper
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.isranascimento.core.R

abstract class BaseToolbarFragment: Fragment() {
    lateinit var toolbar: Toolbar

    abstract fun getToolbarTitle(): String

    protected open fun hasNavigationItem(): Boolean = true

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        toolbar = view.findViewById<Toolbar>(R.id.toolbar).apply {
            this.title = getToolbarTitle()
            if(hasNavigationItem()) {
                this.setNavigationIcon(R.drawable.ic_arrow_back)
                this.navigationIcon?.setTint(Color.WHITE)
                this.setNavigationOnClickListener {
                    activity?.onBackPressed()
                }
            }
        }
    }
}