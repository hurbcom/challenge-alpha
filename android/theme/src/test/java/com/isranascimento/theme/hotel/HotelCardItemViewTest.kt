package com.isranascimento.theme.hotel

import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.children
import androidx.test.runner.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.isranascimento.androidtestutils.TestContextProvider.context
import com.isranascimento.utils.extensions.load
import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.verify
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HotelCardItemViewTest {

    private lateinit var sut: HotelCardItemView
    private val callback: HotelCardItemView.HotelCardItemListener = mockk()

    @Before
    fun setup() {
        sut = HotelCardItemView(context())

        mockkStatic(ImageView::load)
        every { sut.binding.hotelImage.load(any()) } just Runs
        every { callback.onHotelClick(any()) } just Runs
    }

    @Test
    fun `WHEN bind is called THEN it setup the views correctly`() {
        sut.bind(createHotelCardItem())

        verify { sut.binding.hotelImage.load("image") }
        assertThat(sut.binding.hotelTitle.text).isEqualTo("name")
        assertAmenity()
        assertThat(sut.binding.location.text).isEqualTo("city - state")
    }

    @Test
    fun `WHEN bind is called with width THEN it sets the view layout params width correctly`() {
        sut.bind(createHotelCardItem(), 300)
        assertThat(sut.layoutParams.width).isEqualTo(300)
    }

    @Test
    fun `WHEN bind is called without width THEN it sets the view layout params width to match parent`() {
        sut.bind(createHotelCardItem())
        assertThat(sut.layoutParams.width).isEqualTo(ViewGroup.LayoutParams.MATCH_PARENT)
    }

    private fun assertAmenity() {
        assertThat(sut.binding.amenities.childCount).isEqualTo(3)
        val firstAmenity = sut.binding.amenities.children.first()
        assertThat(firstAmenity).isInstanceOf(TextView::class.java)
        firstAmenity as TextView
        assertThat(firstAmenity.text).isEqualTo("• amenity")
    }

    private fun createHotelCardItem() = HotelCardItem(
        "1",
        "name",
        "image",
        "city",
        "state",
        listOf("amenity", "amenity2", "amenity3")
    )

    @Test
    fun `WHEN bindCallback is called and a click occurs THEN the callback is called correctly`() {
        sut.bindListener(createHotelCardItem(), callback)
        sut.binding.root.callOnClick()
        verify { callback.onHotelClick("1") }
    }
}