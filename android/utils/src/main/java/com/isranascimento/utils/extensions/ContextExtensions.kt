package com.isranascimento.utils.extensions

import android.content.Context
import androidx.annotation.DimenRes

fun Context.getDimension(@DimenRes resource: Int) = resources.getDimensionPixelSize(resource)