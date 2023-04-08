package br.com.hurbandroidchallenge.commom.extension

import androidx.lifecycle.SavedStateHandle

fun <T> SavedStateHandle.getArgument(key: String): T? {
    val argument = get<T>(key = key)
    remove<T>(key = key)
    return argument
}

fun <T> SavedStateHandle.putArgument(key: String, value: T?) {
    set(key = key, value = value)
}