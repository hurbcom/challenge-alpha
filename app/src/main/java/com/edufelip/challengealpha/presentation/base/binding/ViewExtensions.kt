package com.edufelip.challengealpha.presentation.base.binding

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.edufelip.challengealpha.presentation.base.utils.ResourceUtils

private fun isValidResourceId(resId: Int) = resId != 0

@BindingAdapter("app:textByResourceName")
fun setStringByResourceName(view: TextView, data: String?) {
    if (data == null)
        return

    val resId = ResourceUtils().getStringResource(view.context, data)

    if (isValidResourceId(resId))
        view.setText(resId)
}