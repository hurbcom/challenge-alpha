package com.isranascimento.utils.extensions

import android.content.Context

object IdentifierUtils {
    fun getItemIdByIdentifier(context: Context, key: String?): Int =
        context.resources.getIdentifier(key, "id", context.packageName)
}