package com.example.challenge_alpha.ui

import android.view.View
import android.widget.RatingBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.challenge_alpha.model.ResultDetailPrice
import java.text.NumberFormat
import java.util.*

@BindingAdapter("alpha:ratingFormat")
fun ratingFormat(view: RatingBar, rate: Float?) {

    if (rate == null) {
        view.visibility = View.INVISIBLE
    } else {
        view.visibility = View.VISIBLE
        view.rating = rate
    }

}

@BindingAdapter("alpha:priceFormat")
fun priceFormat(view: TextView, resultDetailPrice: ResultDetailPrice?) {

    val priceFormat = NumberFormat.getCurrencyInstance(Locale.getDefault())
    priceFormat.currency = Currency.getInstance(resultDetailPrice?.currency ?: "BRL")

    view.text = priceFormat.format(resultDetailPrice?.amount ?: 0f)

}

@BindingAdapter("alpha:setVisibilityOnNull")
fun <T> setVisibility(view: View, data: T?) {

    if (data == null || data == emptyList<T>()) {
        view.visibility = view.visibility
    } else {
        view.visibility = View.VISIBLE
    }

}

@BindingAdapter("alpha:setVisibilityGoneOnNull")
fun <T> setVisibilityGone(view: View, data: T?) {

    if (data == null || data == emptyList<T>()) {
        view.visibility = View.GONE
    } else {
        view.visibility = View.VISIBLE
    }

}

@BindingAdapter("alpha:floatFormatString")
fun <T> floatFormatString(view: TextView, originalFloat: Float?) {

    if (originalFloat == null) {
        view.visibility = View.GONE
    } else {
        view.visibility = View.VISIBLE
        view.text = originalFloat.toInt().toString()
    }

}