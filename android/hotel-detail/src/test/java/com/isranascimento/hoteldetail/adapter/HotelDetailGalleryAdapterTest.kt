package com.isranascimento.hoteldetail.adapter

import android.widget.FrameLayout
import androidx.test.runner.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.isranascimento.androidtestutils.TestContextProvider.context
import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before

import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HotelDetailGalleryAdapterTest {

    private lateinit var sut: HotelDetailGalleryAdapter
    private lateinit var viewGroup: FrameLayout
    @Before
    fun setup() {
        sut = HotelDetailGalleryAdapter(listOf("String"))
        viewGroup = FrameLayout(context())
    }

    @Test
    fun `WHEN onCreateViewHolder is called THEN it returns HotelDetailGalleryItemHolder`() {
        val holder = sut.onCreateViewHolder(viewGroup, 0)

        assertThat(holder).isInstanceOf(HotelDetailGalleryItemHolder::class.java)
    }

    @Test
    fun `WHEN onBindViewHolder is called THEN it calls HotelDetailGalleryItemHolder_bind`() {
        val viewHolderMockk = mockk<HotelDetailGalleryItemHolder>()
        every { viewHolderMockk.bind(any()) } just Runs

        sut.onBindViewHolder(viewHolderMockk, 0)
        verify {
            viewHolderMockk.bind("String")
        }
    }

    @Test
    fun `WHEN getItemCount is called THEN it returns correctly`() {

        assertThat(sut.itemCount).isEqualTo(1)
    }
}