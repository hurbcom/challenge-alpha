package br.com.rvvaranda.hu.Service

import br.com.rvvaranda.hu.BuildConfig
import okhttp3.*
import java.util.*
import java.util.concurrent.TimeUnit

class APIService() {

    companion object {

       const val baseUrl = BuildConfig.URI

        val client = OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(120, TimeUnit.SECONDS)
            .build()


        fun get(endPoint: String, callback: Callback): Call {

            val url = String.format(Locale.getDefault(), "%s/%s",
                baseUrl, endPoint)

            val request = Request.Builder()
                .url(url)
                .get()
                .build()

            val call = client.newCall(request)
            call.enqueue(callback)

            return call
        }
    }
}