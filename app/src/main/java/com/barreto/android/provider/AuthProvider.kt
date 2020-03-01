package com.barreto.android.provider

import com.barreto.android.data.provider.IAuthProvider
import com.barreto.android.di.USER_TOKEN
import org.koin.core.KoinComponent
import org.koin.core.error.MissingPropertyException

class AuthProvider : IAuthProvider, KoinComponent {

    private var token: String? = null
        get() {
            if (field == null) {
                field = getKoin().getProperty(USER_TOKEN)
            }

            return field
        }

    override val hasToken: Boolean
        get() {

            return try {
                token?.isNotBlank() ?: false
            } catch (e: MissingPropertyException) {
                false
            }
        }

    override val accessToken: String
        get() = token ?: ""
}
