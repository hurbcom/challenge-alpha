package com.edufelip.challengealpha.data.data_sources.general_list

import android.content.Context
import com.edufelip.challengealpha.R
import com.edufelip.challengealpha.data.models.GeneralListMenuItemResponse
import com.google.gson.Gson
import java.io.IOException
import java.io.InputStream

class GeneralListMenuLocalDataSourceImpl: GeneralListMenuLocalDataSource {
    override fun getGeneralListMenu(context: Context): List<GeneralListMenuItemResponse> {
        val myJson = inputStreamToString(context.resources.openRawResource(R.raw.main_menu))
        return Gson().fromJson(myJson, Array<GeneralListMenuItemResponse>::class.java).toList()
            .filterNotNull()
    }

    private fun inputStreamToString(inputStream: InputStream): String? {
        return try {
            val bytes = ByteArray(inputStream.available())
            inputStream.read(bytes, 0, bytes.size)
            String(bytes)
        } catch (e: IOException) {
            null
        }
    }
}