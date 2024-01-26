package com.vdemelo.starwarswiki.utils

fun <T> List<T?>?.nonNullOrEmpty(): List<T> {
    return if (this == null) {
        emptyList()
    } else {
        val nonNullableListItems = mutableListOf<T>()
        this.forEach { item ->
            if (item != null) {
                nonNullableListItems.add(item)
            }
        }
        nonNullableListItems
    }
}

fun String.simpleCapitalize(): String = this.replaceFirstChar(Char::titlecase)


//val cachedItems = HashMap<String, Long>()
//
//// TODO fazer o cache funcionar com um tipo só, Species, por exemplo
//// TODO usar o Room pra salvar o cache
//
//fun isCacheValid(
//    cachedItems: HashMap<String, Long>,
//    tag: String,
//    cacheTimeOut: Int
//): Boolean {
//    val latestCacheTime = cachedItems[tag]
//    val timeDiff = System.currentTimeMillis() - (latestCacheTime ?: 0)
//
//    return if (latestCacheTime == null) {
//        false
//    } else {
//        timeDiff < cacheTimeOut
//    }
//}
//
//fun <T> makeRequest(
//    tag: String = "",
//    cacheTimeOut: Int = 0,
//    func: () -> T
//): RequestStatus<T> {
//
//    //TODO isso aqui vai chegar na view e ai trato erro/loading/data
//    val response: RequestStatus<T>? = null
//
//    if (isCacheValid(cachedItems, tag, cacheTimeOut)) {
//        //TODO chama local
//    } else {
//        //TODO chama remoto
//    }
//
//
//    return response ?: RequestStatus.Error(null, "Unknown error") //TODO
//}
