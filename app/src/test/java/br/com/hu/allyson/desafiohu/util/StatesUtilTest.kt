package br.com.hu.allyson.desafiohu.util

import org.junit.Test

import org.junit.Assert.*

class StatesUtilTest {

    @Test
    fun getSigleState() {

        var value = StatesUtil.getSigleState("Acre")
        assertEquals("AC", value)

    }
}