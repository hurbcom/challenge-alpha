package com.isranascimento.core.fragment

import android.graphics.Color
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.annotation.CallSuper
import androidx.annotation.MenuRes
import androidx.appcompat.widget.Toolbar
import androidx.core.view.forEach
import androidx.fragment.app.Fragment
import com.isranascimento.core.R

abstract class BaseToolbarFragment: Fragment() {
    abstract fun getToolbarTitle(): String

    protected open fun hasNavigationItem(): Boolean = true

    @MenuRes
    protected open fun getMenuResource(): Int? = null

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.findViewById<Toolbar>(R.id.toolbar).apply {
            this.title = getToolbarTitle()
            if(hasNavigationItem()) {
                this.setNavigationIcon(R.drawable.ic_arrow_back)
                this.navigationIcon?.setTint(Color.WHITE)
                this.setNavigationOnClickListener {
                    activity?.onBackPressed()
                }
                setupMenuIfNeeded()
            }
        }
    }

    private fun Toolbar.setupMenuIfNeeded() {
        getMenuResource()?.let {
            this.inflateMenu(it)
            this.setOnMenuItemClickListener { item ->
                onMenuItemClick(item)
            }
            this.menu?.forEach { item ->
                item.icon.setTint(Color.WHITE)
            }
        }
    }

    protected open fun onMenuItemClick(menuItem: MenuItem): Boolean {
        return false
    }
}