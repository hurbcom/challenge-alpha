package br.com.hu.allyson.desafiohu.util

import br.com.hu.allyson.desafiohu.domain.Amenity
import org.junit.Assert
import org.junit.Test

import org.junit.Assert.*

class StringUtilsTest {

    @Test
    fun formatAmenities() {

        var amenties = listOf<Amenity>(Amenity("Cortesia 1", "Cortesia 1"),
            Amenity("Cortesia 2", "Cortesia 3"),
            Amenity("Cortesia 3", "Cortesia 3"),
            Amenity("Cortesia 4", "Cortesia 4"))


        var value = StringUtils.formatAmenities(amenties)

        Assert.assertEquals("Cortesia 1, Cortesia 2, Cortesia 3", value)

    }
}