package com.wesleyerick.podracer.util

import com.wesleyerick.podracer.BuildConfig

const val IMAGE_TYPE = ".jpg"
const val SLASH = "/"

enum class TypeEnum(val path: String){
    VEHICLES("vehicles"),
    STARSHIPS("starships"),
}

fun getPhotoUrl(url: String, path: String): String =
    "${BuildConfig.BASE_IMAGE_URL}$path$SLASH${getItemListId(url)}$IMAGE_TYPE"

fun getItemListId(url: String) = if (url.isNotEmpty()) url.split(SLASH).dropLast(1).last() else String()