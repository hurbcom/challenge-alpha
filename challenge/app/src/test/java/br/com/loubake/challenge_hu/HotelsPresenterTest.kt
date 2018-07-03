package br.com.loubake.challenge_hu

import android.content.Context
import br.com.loubake.challenge_hu.data.HotelResults
import br.com.loubake.challenge_hu.hotels.HotelsContract
import br.com.loubake.challenge_hu.hotels.HotelsPresenter
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class HotelsPresenterTest {

    val context: Context = mock()
    val view: HotelsContract.View = mock()

    @InjectMocks
    lateinit var presenter: HotelsPresenter

    @Before
    @Throws(Exception::class)
    fun setUp() {
        presenter = HotelsPresenter(context, view)
    }

    @Test
    fun `sorting list with only hotels and with order`() {

        val hotel3Stars = HotelResults.Hotel().apply {
            this.stars = 3
            this.isHotel = true
            this.isPackage = false
            this.address = HotelResults.Hotel.Address()
            this.amenities = arrayListOf(HotelResults.Hotel.Amenity())
            this.price = HotelResults.Hotel.HotelPrice()
        }

        val hotel2Stars = HotelResults.Hotel().apply {
            this.stars = 2
            this.isHotel = true
            this.isPackage = false
            this.address = HotelResults.Hotel.Address()
            this.amenities = arrayListOf(HotelResults.Hotel.Amenity())
            this.price = HotelResults.Hotel.HotelPrice()
        }

        val hotel1Star = HotelResults.Hotel().apply {
            this.stars = 1
            this.isHotel = true
            this.isPackage = false
            this.address = HotelResults.Hotel.Address()
            this.amenities = arrayListOf(HotelResults.Hotel.Amenity())
            this.price = HotelResults.Hotel.HotelPrice()
        }

        val inputList = arrayListOf(hotel3Stars, hotel3Stars, hotel3Stars, hotel2Stars, hotel2Stars, hotel1Star)

        val expectedList = arrayListOf("3 estrelas", hotel3Stars, hotel3Stars, hotel3Stars,
                "2 estrelas", hotel2Stars, hotel2Stars,
                "1 estrelas", hotel1Star
        )

        given(context.getString(R.string.group_stars, 1)).willReturn("1 estrelas")
        given(context.getString(R.string.group_stars, 2)).willReturn("2 estrelas")
        given(context.getString(R.string.group_stars, 3)).willReturn("3 estrelas")

        Assert.assertEquals(presenter.handleHotelsResponse(inputList), expectedList)
    }

    @Test
    fun `sorting list with only hotels and no orders`() {

        val hotel3Stars = HotelResults.Hotel().apply {
            this.stars = 3
            this.isHotel = true
            this.isPackage = false
            this.address = HotelResults.Hotel.Address()
            this.amenities = arrayListOf(HotelResults.Hotel.Amenity())
            this.price = HotelResults.Hotel.HotelPrice()
        }

        val hotel2Stars = HotelResults.Hotel().apply {
            this.stars = 2
            this.isHotel = true
            this.isPackage = false
            this.address = HotelResults.Hotel.Address()
            this.amenities = arrayListOf(HotelResults.Hotel.Amenity())
            this.price = HotelResults.Hotel.HotelPrice()
        }

        val hotel1Star = HotelResults.Hotel().apply {
            this.stars = 1
            this.isHotel = true
            this.isPackage = false
            this.address = HotelResults.Hotel.Address()
            this.amenities = arrayListOf(HotelResults.Hotel.Amenity())
            this.price = HotelResults.Hotel.HotelPrice()
        }

        val inputList = arrayListOf(hotel1Star, hotel3Stars, hotel1Star, hotel2Stars)

        val expectedList = arrayListOf("3 estrelas", hotel3Stars,
                "2 estrelas", hotel2Stars,
                "1 estrelas", hotel1Star, hotel1Star
        )

        given(context.getString(R.string.group_stars, 1)).willReturn("1 estrelas")
        given(context.getString(R.string.group_stars, 2)).willReturn("2 estrelas")
        given(context.getString(R.string.group_stars, 3)).willReturn("3 estrelas")

        Assert.assertEquals(presenter.handleHotelsResponse(inputList), expectedList)
    }

    @Test
    fun `sorting list with hotels and packages and no orders`() {

        val hotel3Stars = HotelResults.Hotel().apply {
            this.stars = 3
            this.isHotel = true
            this.isPackage = false
            this.address = HotelResults.Hotel.Address()
            this.amenities = arrayListOf(HotelResults.Hotel.Amenity())
            this.price = HotelResults.Hotel.HotelPrice()
        }

        val hotel2Stars = HotelResults.Hotel().apply {
            this.stars = 2
            this.isHotel = true
            this.isPackage = false
            this.address = HotelResults.Hotel.Address()
            this.amenities = arrayListOf(HotelResults.Hotel.Amenity())
            this.price = HotelResults.Hotel.HotelPrice()
        }

        val hotel1Star = HotelResults.Hotel().apply {
            this.stars = 1
            this.isHotel = true
            this.isPackage = false
            this.address = HotelResults.Hotel.Address()
            this.amenities = arrayListOf(HotelResults.Hotel.Amenity())
            this.price = HotelResults.Hotel.HotelPrice()
        }

        val package1 = HotelResults.Hotel().apply {
            this.isHotel = false
            this.isPackage = true
            this.address = HotelResults.Hotel.Address()
            this.amenities = arrayListOf(HotelResults.Hotel.Amenity())
            this.price = HotelResults.Hotel.HotelPrice()
        }

        val package2 = HotelResults.Hotel().apply {
            this.isHotel = false
            this.isPackage = true
            this.address = HotelResults.Hotel.Address()
            this.amenities = arrayListOf(HotelResults.Hotel.Amenity())
            this.price = HotelResults.Hotel.HotelPrice()
        }

        val package3 = HotelResults.Hotel().apply {
            this.isHotel = false
            this.isPackage = true
            this.address = HotelResults.Hotel.Address()
            this.amenities = arrayListOf(HotelResults.Hotel.Amenity())
            this.price = HotelResults.Hotel.HotelPrice()
        }

        val package4 = HotelResults.Hotel().apply {
            this.isHotel = false
            this.isPackage = true
            this.address = HotelResults.Hotel.Address()
            this.amenities = arrayListOf(HotelResults.Hotel.Amenity())
            this.price = HotelResults.Hotel.HotelPrice()
        }

        val inputList = arrayListOf(package1, hotel1Star, package4, hotel3Stars, package2, hotel2Stars, package3)

        val expectedList = arrayListOf("3 estrelas", hotel3Stars,
                "2 estrelas", hotel2Stars,
                "1 estrelas", hotel1Star,
                "Pacotes", package1, package4, package2, package3
        )

        given(context.getString(R.string.group_stars, 1)).willReturn("1 estrelas")
        given(context.getString(R.string.group_stars, 2)).willReturn("2 estrelas")
        given(context.getString(R.string.group_stars, 3)).willReturn("3 estrelas")
        given(context.getString(R.string.group_package)).willReturn("Pacotes")

        Assert.assertEquals(presenter.handleHotelsResponse(inputList), expectedList)
    }

    @Test
    fun `sorting list with only packages`() {

        val package1 = HotelResults.Hotel().apply {
            this.isHotel = false
            this.isPackage = true
            this.address = HotelResults.Hotel.Address()
            this.amenities = arrayListOf(HotelResults.Hotel.Amenity())
            this.price = HotelResults.Hotel.HotelPrice()
        }

        val package2 = HotelResults.Hotel().apply {
            this.isHotel = false
            this.isPackage = true
            this.address = HotelResults.Hotel.Address()
            this.amenities = arrayListOf(HotelResults.Hotel.Amenity())
            this.price = HotelResults.Hotel.HotelPrice()
        }

        val package3 = HotelResults.Hotel().apply {
            this.isHotel = false
            this.isPackage = true
            this.address = HotelResults.Hotel.Address()
            this.amenities = arrayListOf(HotelResults.Hotel.Amenity())
            this.price = HotelResults.Hotel.HotelPrice()
        }

        val package4 = HotelResults.Hotel().apply {
            this.isHotel = false
            this.isPackage = true
            this.address = HotelResults.Hotel.Address()
            this.amenities = arrayListOf(HotelResults.Hotel.Amenity())
            this.price = HotelResults.Hotel.HotelPrice()
        }

        val inputList = arrayListOf(package1, package2, package4, package3)

        val expectedList = arrayListOf("Pacotes", package1, package2, package4, package3)

        given(context.getString(R.string.group_package)).willReturn("Pacotes")

        Assert.assertEquals(presenter.handleHotelsResponse(inputList), expectedList)
    }
}