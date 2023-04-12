package br.com.vaniala.starwars.ui.home.adapter

import br.com.vaniala.starwars.databinding.ListItemCategoryBinding
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Test

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 10/04/23.
 *
 */
class CategoryViewHolderTest {

    private lateinit var binding: ListItemCategoryBinding
    private lateinit var viewHolder: CategoryViewHolder

    @Before
    fun setUp() {
        binding = mockk(relaxed = true)
        viewHolder = CategoryViewHolder(binding)
    }

    @Test
    fun `should return string with starships url`() {
        val typeCategory = "starships"
        val expect = "https://starwars-visualguide.com/assets/img/categories/starships.jpg"

        val result = viewHolder.getUrlImage(typeCategory)

        assertEquals(expect, result)
    }

    @Test
    fun `should return a url empty`() {
        val typeCategory = ""
        val expect = ""

        val result = viewHolder.getUrlImage(typeCategory)

        assertEquals(expect, result)
    }

    @Test
    fun `should return string with character url`() {
        val typeCategory = "people"
        val expect = "https://starwars-visualguide.com/assets/img/categories/character.jpg"

        val result = viewHolder.getUrlImage(typeCategory)

        assertEquals(expect, result)
    }

    @Test
    fun `should return a url with error`() {
        val typeCategory = "peoples"
        val expect = "https://starwars-visualguide.com/assets/img/categories/character.jpg"

        val result = viewHolder.getUrlImage(typeCategory)

        assertNotEquals(expect, result)
    }
}
