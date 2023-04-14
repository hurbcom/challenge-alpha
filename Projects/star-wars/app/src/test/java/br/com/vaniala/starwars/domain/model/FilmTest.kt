package br.com.vaniala.starwars.domain.model

import org.junit.Assert
import org.junit.Test

class FilmTest {

    @Test
    fun `should return string with id when url length is greater than 2`() {
        val expect = "https://starwars-visualguide.com/assets/img/films/1.jpg"
        val film = Film(
            title = "A New Hope",
            url = "https://swapi.dev/api/films/1/",
        )

        val result = film.image
        Assert.assertEquals(expect, result)
    }

    @Test
    fun `should return string with id when url length value is equals 2`() {
        val expect = "https://starwars-visualguide.com/assets/img/films/2.jpg"
        val film = Film(
            title = "A New Hope",
            url = "2/",
        )

        val result = film.image
        Assert.assertEquals(expect, result)
    }

    @Test
    fun `should return null  when url length value is less than 2`() {
        val film = Film(
            title = "A New Hope",
            url = "/",
        )

        val result = film.image
        Assert.assertNull(result)
    }

    @Test
    fun `should return null  when url is empty`() {
        val film = Film(
            title = "A New Hope",
            url = "",
        )
        val result = film.image
        Assert.assertNull(result)
    }

    @Test
    fun `should return null  when url is null`() {
        val film = Film(
            title = "A New Hope",
            url = null,
        )
        val result = film.image
        Assert.assertNull(result)
    }

    @Test
    fun `should return string with episode and tile`() {
        val expect = "Episode IV: A New Hope"
        val film = Film(
            title = "A New Hope",
            episode_id = 4,
        )

        val result = film.titleFormatted
        Assert.assertEquals(expect, result)
    }

    @Test
    fun `should return string with value IV when episode equals 4`() {
        val expect = "Episode IV: A New Hope"
        val filmWrong = Film(
            title = "A New Hope",
            episode_id = 3,
        )
        val filmCorrect = Film(
            title = "A New Hope",
            episode_id = 4,
        )

        val resultNotEquals = filmWrong.titleFormatted
        Assert.assertNotEquals(expect, resultNotEquals)
        val resultEquals = filmCorrect.titleFormatted
        Assert.assertEquals(expect, resultEquals)
    }

    @Test
    fun `should return null when episode is null`() {
        val film = Film(
            title = "A New Hope",
            episode_id = null,
        )

        val result = film.titleFormatted
        Assert.assertNull(result)
    }

    @Test
    fun `should return null when episode is greater than 6 or less than 1`() {
        val filmEpisodeGreater = Film(
            title = "A New Hope",
            episode_id = 7,
        )
        val filmEpisodeLess = Film(
            title = "A New Hope",
            episode_id = 0,
        )

        val resultGreater = filmEpisodeGreater.titleFormatted
        val resultLess = filmEpisodeLess.titleFormatted
        Assert.assertNull(resultGreater)
        Assert.assertNull(resultLess)
    }

    @Test
    fun `should return string with openingCrawl without spaces`() {
        val expect = "It is a period of civil war. Rebel spaceships, striking from a hidden base"
        val film = Film(
            title = "A New Hope",
            episode_id = 3,
            opening_crawl = "It is a period of civil war.\r\n Rebel spaceships, striking\r\n from a hidden base",
        )

        val result = film.openingCrawl
        Assert.assertEquals(expect, result)
    }

    @Test
    fun `should return null when openingCrawl null`() {
        val film = Film(
            title = "A New Hope",
            episode_id = 3,
        )

        val result = film.openingCrawl
        Assert.assertNull(result)
    }

    @Test
    fun `should return string with only year`() {
        val expect = "1977"
        val film = Film(
            title = "A New Hope",
            release_date = "1977-05-25",
        )

        val result = film.releaseDate
        Assert.assertEquals(expect, result)
    }

    @Test
    fun `should return null when releaseDate is null`() {
        val film = Film(
            title = "A New Hope",
        )

        val result = film.releaseDate
        Assert.assertNull(result)
    }
}
