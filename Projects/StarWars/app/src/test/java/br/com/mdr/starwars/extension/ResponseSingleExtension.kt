package br.com.mdr.starwars.extension

import android.content.Context
import androidx.test.core.app.ApplicationProvider

fun getJsonFromAssetsOrResources(fileName: String): String {
    return try {
        val context = ApplicationProvider.getApplicationContext<Context>()
        context.assets.open(fileName).bufferedReader().readText()
    } catch (ex: Exception) {
        fileName::javaClass::class.java.classLoader?.getResource(fileName)?.readText()
            ?: throw IllegalArgumentException("Invalid json")
    }
}
