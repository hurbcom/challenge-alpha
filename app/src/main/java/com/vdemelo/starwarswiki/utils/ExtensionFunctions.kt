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

//fun doViewWork() {
//    //TODO
//}
//
//fun doViewModelWork() {
//    val liveData = makeAsyncTask {
//
//    }
//}
//
//val timeHashMap = java.util.HashMap<String, Long>()
//
//// TODO fazer o cache funcionar com um tipo só, Species, por exemplo
//// TODO usar o Room pra salvar o cache
//
//fun <T> makeAsyncTask(
//    tag: String = "",
//    cacheTimeOut: Int = 0,
//    func: () -> T
//): LiveData<RequestStatus<T>> {
//
//    val liveData = MutableLiveData<RequestStatus<T>>() //TODO isso aqui vai chegar na view e ai trato erro/loading/data
//
//    val latestCacheTime = timeHashMap[tag]
//    if (latestCacheTime != null) {
//        //TODO
//    }
//
//    val className = T::class.java.packageName
//
//    // 1. ve se o cache existe e é válido
//    // 2. recupera o cache se valido ou faz request se invalido
//    // 3. devolve do use case pra view model
//
//    async {
//        if (cacheTimeOut > 0) {
//            val timeDiff = System.currentTimeMillis() - (timeHashMap[tag] ?: 0)
//            if (timeDiff > cacheTimeOut) {
//                val result = func.invoke()
//                cacheItem?.put(tag, result)
//            }
//        } else {
//            //TODO
//        }
//    }
//
//    return MutableLiveData<RequestStatus<T>>()
//}
