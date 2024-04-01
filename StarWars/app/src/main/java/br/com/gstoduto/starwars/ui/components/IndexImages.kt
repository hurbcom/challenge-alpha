package br.com.gstoduto.starwars.ui.components

import androidx.compose.runtime.Composable

@Composable
fun getIndexFromUrl(index: String, token: String) = index
        .substring(index.length - (token.length+7))
        .split(token)
        .drop(1)
        .joinToString("")
        .split("/").dropLast(1)
        .joinToString("")

fun getIndex(index: String, token: String) = index
    .substring(index.length - (token.length+7))
    .split(token)
    .drop(1)
    .joinToString("")
    .split("/").dropLast(1)
    .joinToString("")