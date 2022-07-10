package com.edufelip.challengealpha.presentation.base.utils

import android.content.Context

const val STRING_IDENTIFIER = "string"
const val DRAWABLE_IDENTIFIER = "drawable"
const val RAW_IDENTIFIER = "raw"

class ResourceUtils {
    private fun getPackageName(context: Context) = context.packageName

    fun getStringResource(context: Context, resourceName: String): Int{
        return getResource(
            context = context,
            resourceIdentifier = STRING_IDENTIFIER,
            resourceName = resourceName
        )
    }

    fun getDrawableResource(context: Context, resourceName: String): Int{
        return getResource(
            context = context,
            resourceIdentifier = DRAWABLE_IDENTIFIER,
            resourceName = resourceName
        )
    }

    fun getRawResource(context: Context, resourceName: String): Int{
        return getResource(
            context = context,
            resourceIdentifier = RAW_IDENTIFIER,
            resourceName = resourceName
        )
    }

    private fun getResource(context: Context, resourceIdentifier: String, resourceName: String): Int{
        return context.resources.getIdentifier(
            resourceName,
            resourceIdentifier,
            getPackageName(context)
        )
    }
}