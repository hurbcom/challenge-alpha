package com.isranascimento.theme.sharedcomponents

import androidx.test.runner.AndroidJUnit4
import com.isranascimento.androidtestutils.TestContextProvider.context
import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ConnectionErrorViewTest {

    private lateinit var sut: ConnectionErrorView
    private val onTryAgainClickMock = mockk<ConnectionErrorView.ConnectionErrorListener>()

    @Before
    fun setup() {
        every { onTryAgainClickMock.onTryAgainClick() } just Runs
        sut = ConnectionErrorView(context(), null)
    }

    @Test
    fun `WHEN onTryAgainClickListener is setted and a button click occurs THEN the callback is called correcly`() {
        sut.setOnTryAgainClickListener(onTryAgainClickMock)
        sut.binding.tryAgainButton.callOnClick()
        verify {
            onTryAgainClickMock.onTryAgainClick()
        }
    }
}