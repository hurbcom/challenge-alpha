package com.wesleyerick.podracer.util

import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData

fun View.gone() {
    this.visibility = View.GONE
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun <T> LiveData<T>.listener(lifecycleOwner: LifecycleOwner, onData: (T) -> Unit) {
    return this.observe(lifecycleOwner) { it?.apply { onData(this) } }
}
