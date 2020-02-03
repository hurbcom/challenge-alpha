package br.com.flyingdutchman.challenge_alpha

import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.TextUtils
import android.text.style.*
import android.view.View
import com.google.android.material.snackbar.Snackbar
import java.text.DecimalFormat
import java.util.*

fun Double.formatForBrazilianCurrency(): String {
    val brazilianFormat = DecimalFormat
        .getCurrencyInstance(Locale("pt", "br"))
    return brazilianFormat.format(this)
}

fun Double.formatDecimal(): String {
    return String.format("%.0f", this)
}


fun spannable(func: () -> SpannableString) = func()
private fun span(s: CharSequence, o: Any) =
    (if (s is String) SpannableString(s) else s as? SpannableString
        ?: SpannableString("")).apply { setSpan(o, 0, length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE) }

operator fun SpannableString.plus(s: SpannableString) = SpannableString(TextUtils.concat(this, s))
operator fun SpannableString.plus(s: String) = SpannableString(TextUtils.concat(this, s))

fun bold(s: CharSequence) = span(
    s,
    StyleSpan(Typeface.BOLD)
)

fun italic(s: CharSequence) = span(
    s,
    StyleSpan(Typeface.ITALIC)
)

fun underline(s: CharSequence) =
    span(s, UnderlineSpan())

fun strike(s: CharSequence) =
    span(s, StrikethroughSpan())

fun sup(s: CharSequence) =
    span(s, SuperscriptSpan())

fun sub(s: CharSequence) =
    span(s, SubscriptSpan())

fun size(size: Float, s: CharSequence) =
    span(s, RelativeSizeSpan(size))

fun color(color: Int, s: CharSequence) =
    span(s, ForegroundColorSpan(color))

fun background(color: Int, s: CharSequence) =
    span(s, BackgroundColorSpan(color))

fun url(url: String, s: CharSequence) =
    span(s, URLSpan(url))


fun View.snackBar(
    snackBarText: CharSequence,
    duration: Int = Snackbar.LENGTH_LONG,
    messageAction: CharSequence = "Reload",
    listener: () -> Unit? = {}
): Snackbar {
    return Snackbar.make(this, snackBarText, duration)
        .setAction(messageAction) {
            listener.invoke()
        }
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.INVISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}


