package com.github.felipehjcosta.huchallenge.base.utils


fun readResourceFile(path: String): String? = ClassLoader.getSystemClassLoader()
        .getResourceAsStream(path)
        ?.use {
            it.reader().readText()
        }