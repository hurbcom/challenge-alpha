package com.isranascimento.hotelslist.ui.adapter.holder

import android.view.LayoutInflater
import androidx.test.filters.SmallTest
import androidx.test.runner.AndroidJUnit4
import androidx.test.runner.AndroidJUnitRunner
import com.google.common.truth.Truth.assertThat
import com.isranascimento.androidtestutils.TestContextProvider.context
import com.isranascimento.hotelslist.databinding.HotelListTitleItemBinding
import com.isranascimento.hotelslist.ui.models.HotelListUITitle
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RuntimeEnvironment

@SmallTest
@RunWith(AndroidJUnit4::class)
class HotelListTitleHolderTest {

    private lateinit var sut: HotelListTitleHolder
    private lateinit var binding: HotelListTitleItemBinding

    @Before
    fun setup() {
        binding = HotelListTitleItemBinding.inflate(LayoutInflater.from(context()))
        sut = HotelListTitleHolder(
            binding
        )
    }

    @Test
    fun `WHEN a title is bound THEN the holder sets the starCount to ratingView`() {
        sut.bind(HotelListUITitle(3f))
        assertThat(binding.rating.rating).isEqualTo(3f)
    }
}