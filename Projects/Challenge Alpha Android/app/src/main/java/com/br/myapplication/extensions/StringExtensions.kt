package com.br.myapplication.extensions

fun String.categoryImageUrl(category: String) : String {
    val radicalUrl = "https://starwars-visualguide.com/assets/img/"
    val extensionUrl = ".jpg"
    val list = this.split("/")
    return radicalUrl + category +"/"+ list[5]  + extensionUrl
}