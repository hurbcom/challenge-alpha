package com.br.natanbrito.challenge.alpha.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

fun Context.hasInternetConnection(): Boolean {
    val connectivityManager =
        this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    val actNw = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        ?: return false

    return actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
        actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
        actNw.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
}
