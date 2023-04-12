package br.com.vaniala.starwars.ui.film.adapter

import br.com.vaniala.starwars.databinding.ListItemFilmsBinding
import io.mockk.mockk
import org.junit.Assert
import org.junit.Before
import org.junit.Test
/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 12/04/23.
 *
 */
class FilmsViewHolderTest {

    private lateinit var binding: ListItemFilmsBinding
    private lateinit var viewHolder: FilmsViewHolder

    @Before
    fun setUp() {
        binding = mockk(relaxed = true)
        viewHolder = FilmsViewHolder(binding)
    }

    @Test
    fun `should return string with id when url length is greater than 2`() {
        val url = "https://swapi.dev/api/films/1/"
        val expect = "https://starwars-visualguide.com/assets/img/films/1.jpg"

        val result = viewHolder.getUrlImage(url)

        Assert.assertEquals(expect, result)
    }

    @Test
    fun `should return string with id when url length value is equals 2`() {
        val url = "2/"
        val expect = "https://starwars-visualguide.com/assets/img/films/2.jpg"

        val result = viewHolder.getUrlImage(url)

        Assert.assertEquals(expect, result)
    }

    @Test
    fun `should return null  when url length value is less than 2`() {
        val url = "/"

        val result = viewHolder.getUrlImage(url)

        Assert.assertNull(result)
    }

    @Test
    fun `should return null  when url is empty`() {
        val url = ""

        val result = viewHolder.getUrlImage(url)

        Assert.assertNull(result)
    }

    @Test
    fun `should return null  when url is null`() {
        val url = null

        val result = viewHolder.getUrlImage(url)

        Assert.assertNull(result)
    }
}
