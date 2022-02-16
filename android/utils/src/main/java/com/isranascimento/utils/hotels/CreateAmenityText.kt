package com.isranascimento.utils.hotels

import android.content.Context
import android.widget.LinearLayout
import android.widget.TextView
import com.isranascimento.utils.R

fun createAmenityTextview(context: Context, amenity: String) = TextView(context).apply {
    this.layoutParams = LinearLayout.LayoutParams(
        LinearLayout.LayoutParams.MATCH_PARENT,
        LinearLayout.LayoutParams.WRAP_CONTENT
    )
    this.text = context.getString(R.string.bullet_icon_text, amenity)
}