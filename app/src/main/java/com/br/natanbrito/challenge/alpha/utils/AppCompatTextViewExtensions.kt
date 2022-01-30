package com.br.natanbrito.challenge.alpha.utils

import android.text.Spannable
import android.text.SpannableString
import android.text.style.RelativeSizeSpan
import androidx.appcompat.widget.AppCompatTextView
import com.br.natanbrito.challenge.alpha.R
import com.br.natanbrito.challenge.data.model.results.Address
import com.br.natanbrito.challenge.data.model.results.PriceResults

const val SIZE_1_25 = 1.25f

fun AppCompatTextView.prepareCurrencyText(textString: Int, price: PriceResults) {
    val convertedPrice = price.currentPrice.toString().replace(".", ",")
    text = context.getString(textString, convertedPrice)
}

fun AppCompatTextView.prepareLocationText(address: Address) {
    text = context.getString(R.string.location, address.city, address.state)
}

fun AppCompatTextView.customizeText(text: String) {
    val spannable = SpannableString(text)
    spannable.setSpan(
        RelativeSizeSpan(SIZE_1_25),
        0,
        text.indexOf(":"),
        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
    )

    this.text = spannable
}