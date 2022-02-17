package com.isranascimento.hoteldetail.adapter

import android.view.LayoutInflater
import android.widget.ImageView
import androidx.test.runner.AndroidJUnit4
import com.isranascimento.androidtestutils.TestContextProvider
import com.isranascimento.hoteldetail.databinding.HotelDetailGalleryHolderBinding
import com.isranascimento.utils.extensions.load
import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockkStatic
import io.mockk.verify
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HotelDetailViewHolderTest {
    private lateinit var sut: HotelDetailGalleryItemHolder
    private var _binding: HotelDetailGalleryHolderBinding? = null
    private val binding
        get() = _binding!!

    @Before
    fun setup() {
        _binding = HotelDetailGalleryHolderBinding.inflate(LayoutInflater.from(TestContextProvider.context()))
        mockkStatic(ImageView::load)
        every { binding.image.load(any()) } just Runs

        sut = HotelDetailGalleryItemHolder(binding)
    }

    @Test
    fun `WHEN bind is called THEN it loads the image`() {
        sut.bind("image")

        verify {
            binding.image.load("image")
        }
    }
}