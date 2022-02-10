package com.isranascimento.challengealpha

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class TestCoverTest {
    val sut = TestCover()

    @Test
    fun `WHEN sum is called THEN it sums correctly`() {
        assertThat(sut.sum(2, 2), `is`(4))
    }
}