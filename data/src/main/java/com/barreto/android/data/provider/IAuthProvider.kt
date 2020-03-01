package com.barreto.android.data.provider

interface IAuthProvider {
    val hasToken: Boolean
    val accessToken: String
}
