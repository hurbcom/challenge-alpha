package com.barreto.android.data.provider

import okhttp3.Headers.Builder

internal fun Builder.addIfNotPresent(name: String, value: String) {
    if (name.isEmpty() || value.isEmpty()) return

    if (this[name] == null) {
        this.add(name, value)
    }
}
