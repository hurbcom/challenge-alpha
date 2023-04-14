package br.com.hurbandroidchallenge

import br.com.hurbandroidchallenge.commom.util.date.DateUtils
import org.junit.Test
import kotlin.test.assertEquals

class DateUtilsTest {

    @Test
    fun testDaysBetweenDates() {
        val first = "2023-01-09"
        val second = "2023-01-12"
        val days = DateUtils.daysBetweenDates(first = first, second = second)
        assertEquals(days, 3L)
    }

    @Test
    fun testGetClientPatterDate() {
        val date = DateUtils.getClientPatternDate("2023-01-09")
        assertEquals(date, "09/01/2023")
    }

    @Test
    fun testGetServerPatterDate() {
        val date = DateUtils.getServerPatternDate("12/01/2023")
        assertEquals(date, "2023-01-12")
    }

}