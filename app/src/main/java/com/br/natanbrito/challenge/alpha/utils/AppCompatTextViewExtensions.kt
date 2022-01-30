package com.br.natanbrito.challenge.alpha.utils

import android.text.Spannable
import android.text.SpannableString
import android.text.TextUtils
import android.text.style.RelativeSizeSpan
import androidx.appcompat.widget.AppCompatTextView
import com.br.natanbrito.challenge.alpha.R
import com.br.natanbrito.challenge.alpha.hotel.list.INITIAL_POSITION
import com.br.natanbrito.challenge.alpha.hotel.list.MAX_AMENITIES_COUNT
import com.br.natanbrito.challenge.data.model.results.Address
import com.br.natanbrito.challenge.data.model.results.AmenityResults
import com.br.natanbrito.challenge.data.model.results.PriceResults
import java.text.DecimalFormat

const val SIZE_1_25 = 1.25f

fun AppCompatTextView.prepareCurrencyText(textString: Int, price: PriceResults) {
    val formatter = DecimalFormat("#,###,###.00")
    val convertedPrice = formatter.format(price.currentPrice)
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

fun AppCompatTextView.setupAmenities(amenities: List<AmenityResults>, isHome: Boolean) {
    text = if (isHome) {
        TextUtils.join(
            ", ",
            amenities.filter {
                !it.name.contains(context.getString(R.string.additional_cost))
            }.subList(INITIAL_POSITION, MAX_AMENITIES_COUNT).map { amenity -> amenity.name }
        )
    } else {
        TextUtils.join(
            ", ",
            amenities.filter {
                !it.name.contains(context.getString(R.string.additional_cost))
            }.map { amenity -> amenity.name }
        )
    }
}
