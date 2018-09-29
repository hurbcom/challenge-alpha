package br.com.hu.allyson.desafiohu

import okio.Okio
import java.io.IOException
import java.nio.charset.Charset

object IOUtils {

    @Throws(IOException::class)
    fun readStringFromResourcePath(path: String): String {
        val input = IOUtils::class.java.classLoader.getResourceAsStream(path)
        val bufferedSource = Okio.buffer(Okio.source(input))
        return bufferedSource.readString(Charset.defaultCharset())
    }
}