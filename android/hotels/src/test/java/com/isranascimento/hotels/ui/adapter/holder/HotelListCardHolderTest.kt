package com.isranascimento.hotels.ui.adapter.holder

import com.isranascimento.hotels.util.createHotelUIItem
import com.isranascimento.theme.hotel.HotelCardItemView
import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class HotelListCardHolderTest {

    private lateinit var sut: HotelListCardHolder

    val cardItemMock: HotelCardItemView = mockk()

    private val listener = object: HotelCardItemView.HotelCardItemListener {
        override fun onHotelClick(hotelId: String) = Unit
    }

    @Before
    fun setup() {
        sut = HotelListCardHolder(cardItemMock)
    }

    @Test
    fun `WHEN bind is called THEN it calls the view bind correctly`() {
        every { cardItemMock.bind(any()) } just Runs

        sut.bind(createHotelUIItem(0))

        verify(exactly = 1) {
            cardItemMock.bind(createHotelUIItem(0).card)
        }
    }

    @Test
    fun `WHEN bindCallback is called THEN it calls view bindCallback correctly`() {
        every { cardItemMock.bindListener(any(), any()) } just Runs

        sut.bindListener(createHotelUIItem(1), listener)

        verify {
            cardItemMock.bindListener(createHotelUIItem(1).card, listener)
        }
    }

//    private var _binding: HotelListCardItemBinding? = null
//    private val binding
//        get() = _binding!!
//
//    private lateinit var callbackOnClick: (String) -> Unit
//    @Before
//    fun setup() {
//        _binding = HotelListCardItemBinding.inflate(LayoutInflater.from(TestContextProvider.context()))
//
//        mockkStatic(ImageView::load)
//        every { binding.hotelImage.load(any()) } just Runs
//
//        sut = HotelListCardHolder(binding)
//    }
//
//    @After
//    fun tearDown() {
//        _binding = null
//    }
//
//    @Test
//    fun `WHEN a hotelItem is bound THEN the hotel properties is set to views`() {
//
//        sut.bind(createHotelUIItem(1))
//
//        verify { binding.hotelImage.load("Image 1") }
//        assertThat(binding.hotelTitle.text).isEqualTo("Hotel 1")
//        assertAmenity()
//        assertThat(binding.location.text).isEqualTo("City 1 - State 1")
//    }
//
//    @Test
//    fun `WHEN a click ocurrs THEN the callbackFunction is called with correct SKU`() {
//
//        callbackOnClick = mockk()
//        every { callbackOnClick(any()) } just Runs
//
//        sut.bind(createHotelUIItem(1))
//        sut.setClickListener { callbackOnClick.invoke(it) }
//        binding.root.callOnClick()
//        verify {
//            callbackOnClick("1")
//        }
//    }
//
//    private fun assertAmenity() {
//        assertThat(binding.amenities.childCount).isEqualTo(3)
//        val firstAmenity = binding.amenities.children.first()
//        assertThat(firstAmenity).isInstanceOf(TextView::class.java)
//        firstAmenity as TextView
//        assertThat(firstAmenity.text).isEqualTo("• Amenity 1")
//    }
}