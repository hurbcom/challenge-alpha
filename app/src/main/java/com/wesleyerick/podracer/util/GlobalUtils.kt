package com.wesleyerick.podracer.util

const val IMAGE_TYPE = ".jpg"

enum class ImageTypeEnum(val path: String){
    VEHICLES("vehicles"),
    STARSHIPS("starships"),
}

fun getPhotoUrl(url: String, path: String): String =
    "https://starwars-visualguide.com/assets/img/$path/${getItemListId(url)}$IMAGE_TYPE"

fun getItemListId(url: String) = if (url.isNotEmpty()) url.split("/").dropLast(1).last() else String()