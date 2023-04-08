package br.com.hurbandroidchallenge.commom.extension

infix fun <T> T?.ifNull(other: T): T = this ?: other