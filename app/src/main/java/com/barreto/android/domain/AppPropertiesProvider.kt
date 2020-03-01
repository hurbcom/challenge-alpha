package com.barreto.android.domain

import android.content.Context
import android.os.Build
import android.webkit.WebSettings
import com.barreto.android.domain.util.IClientPropertiesProvider
import com.barreto.android.BuildConfig
import timber.log.Timber

open class AppPropertiesProvider(private val context: Context) : IClientPropertiesProvider {

    private val _userAgent by lazy { USER_AGENT + WebSettings.getDefaultUserAgent(context) }

    override val userAgent get() = _userAgent

    private val _device by lazy {
        mapOf(
                "app_version" to BuildConfig.VERSION_NAME,
                "os_name" to getDeviceOSName(),
                "os_version" to Build.VERSION.RELEASE,
                "model" to getDeviceModelName(),
                "app_build" to BuildConfig.VERSION_CODE.toString()
        )
    }

    override val device: Map<String, String> get() = _device

    private fun getDeviceOSName(): String {
        val builder = StringBuilder()
        val fields = Build.VERSION_CODES::class.java.fields
        for (field in fields) {
            val fieldName = field.name
            var fieldValue = -1

            try {
                fieldValue = field.getInt(Any())
            } catch (e: Exception) {
                Timber.e(e)
            }

            if (fieldValue == Build.VERSION.SDK_INT) {
                builder.append("Android").append(" : ")
                builder.append(fieldName).append(" : ")
                builder.append("sdk=").append(fieldValue)
            }
        }

        return builder.toString()
    }

    private fun getDeviceModelName(): String {
        val manufacturer = Build.MANUFACTURER
        val model = Build.MODEL
        return if (model.startsWith(manufacturer)) {
            model
        } else {
            "$manufacturer $model"
        }
    }

    companion object {
        val USER_AGENT = String.format("Project Android(%s)/%s(%s);",
                BuildConfig.APPLICATION_ID,
                BuildConfig.VERSION_NAME,
                BuildConfig.VERSION_CODE)
    }
}
