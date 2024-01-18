package com.vdemelo.starwarswiki.utils

fun <T> List<T?>?.nonNullOrEmpty(): List<T> {
    return if (this == null) {
        emptyList()
    }
    else {
        val nonNullableListItems = mutableListOf<T>()
        this.forEach { item ->
            if (item != null) {
                nonNullableListItems.add(item)
            }
        }
        nonNullableListItems
    }
}
