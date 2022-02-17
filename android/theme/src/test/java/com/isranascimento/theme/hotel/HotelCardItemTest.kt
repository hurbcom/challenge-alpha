package com.isranascimento.theme.hotel

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class HotelCardItemTest {
    private val sut = HotelCardItem(
        "1",
        "name",
        "image",
        "city",
        "state",
        listOf("amenity")
    )

    @Test
    fun `WHEN all other component props are equal THEN areContentTheSame returns true`() {
        val returnedValue = sut.areContentTheSame(
            HotelCardItem(
                "1",
                "name",
                "image",
                "city",
                "state",
                listOf("amenity")
            )
        )

        assertThat(returnedValue).isTrue()
    }

    @Test
    fun `WHEN another component props is different THEN areContentTheSame returns false`() {
        val returnedValue = sut.areContentTheSame(
            HotelCardItem(
                "1",
                "name",
                "another prop",
                "city",
                "state",
                listOf("amenity")
            )
        )

        assertThat(returnedValue).isFalse()
    }

    @Test
    fun `WHEN the id is different THEN areItemsTheSame returns false`() {
        val returnedValue = sut.areItemsTheSame(
            HotelCardItem(
                "Different ID",
                "name",
                "another prop",
                "city",
                "state",
                listOf("amenity")
            )
        )
        assertThat(returnedValue).isFalse()
    }

    @Test
    fun `WHEN the id is equal THEN areItemsTheSame returns true`() {
        val returnedValue = sut.areItemsTheSame(
            HotelCardItem(
                "1",
                "name",
                "another prop",
                "city",
                "state",
                listOf("amenity")
            )
        )
        assertThat(returnedValue).isTrue()
    }
}