package com.isranascimento.utils.extensions

import android.content.Context
import android.util.DisplayMetrics
import android.view.WindowManager
import androidx.annotation.DimenRes

fun Context.getDimension(@DimenRes resource: Int) = resources.getDimensionPixelSize(resource)

fun Context.screenWidthInPx(): Int {
    val displayMetrics = DisplayMetrics()
    val windowManager = this.getSystemService(Context.WINDOW_SERVICE) as WindowManager
    // Context.getDisplay requires api 30
    windowManager.defaultDisplay.getMetrics(displayMetrics)
    return displayMetrics.widthPixels
}