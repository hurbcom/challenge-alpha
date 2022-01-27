package com.br.natanbrito.challenge.alpha.utils

import androidx.appcompat.widget.AppCompatTextView
import com.br.natanbrito.challenge.alpha.R
import com.br.natanbrito.challenge.data.model.results.Address
import com.br.natanbrito.challenge.data.model.results.PriceResults

fun AppCompatTextView.prepareCurrencyText(price: PriceResults) {
    val convertedPrice = price.current_price.toString().replace(".", ",")
    text = context.getString(R.string.hotel_price, convertedPrice)
}

fun AppCompatTextView.prepareLocationText(address: Address) {
    text = context.getString(R.string.location, address.city, address.state)
}
