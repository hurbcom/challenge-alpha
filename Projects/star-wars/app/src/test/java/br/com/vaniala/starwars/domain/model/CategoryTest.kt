package br.com.vaniala.starwars.domain.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class CategoryTest {

    @Test
    fun `should return string with starships url`() {
        val expect = "https://starwars-visualguide.com/assets/img/categories/starships.jpg"
        val category = Category(
            name = "starships",
        )
        val result = category.image
        assertEquals(expect, result)
    }

    @Test
    fun `should return a url empty when name is empty`() {
        val category = Category(
            name = "",
        )

        val result = category.image
        assertNull(result)
    }

    @Test
    fun `should return string with character url`() {
        val expect = "https://starwars-visualguide.com/assets/img/categories/character.jpg"
        val category = Category(
            name = "people",
        )

        val result = category.image
        assertEquals(expect, result)
    }
}
