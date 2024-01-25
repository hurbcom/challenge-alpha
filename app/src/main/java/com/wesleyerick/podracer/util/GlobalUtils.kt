package com.wesleyerick.podracer.util

import android.content.Context
import com.wesleyerick.podracer.BuildConfig
import com.wesleyerick.podracer.R

const val IMAGE_TYPE = ".jpg"
const val SLASH = "/"
const val BLANK = ""

enum class TypeEnum(val path: String) {
    VEHICLES("vehicles"),
    STARSHIPS("starships"),
}

fun getPhotoUrl(url: String, path: String): String =
    "${BuildConfig.BASE_IMAGE_URL}$path$SLASH${getItemListId(url)}$IMAGE_TYPE"

fun getItemListId(url: String) =
    if (url.isNotEmpty()) url.split(SLASH).dropLast(1).last() else String()

fun getSubtitleText(
    context: Context,
    beforeValue: String,
    value: String,
    afterValue: String = BLANK
) =
    context.getString(
        if (afterValue.isEmpty()) {
            R.string.subtitle_before_value
        } else {
            R.string.subtitle_before_value_after
        },
        beforeValue,
        value,
        afterValue.ifEmpty { null }
    )
