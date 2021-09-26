package com.filipeoliveira.hurbchallenge.ui.utils

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("android:isVisible")
fun isVisible(view: View, isVisible: Boolean){
    if (isVisible){
        view.visibility = View.VISIBLE
    } else {
        view.visibility = View.GONE
    }
}