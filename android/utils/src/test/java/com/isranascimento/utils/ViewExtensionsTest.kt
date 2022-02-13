package com.isranascimento.utils

import android.view.View
import androidx.test.runner.AndroidJUnit4
import com.google.common.truth.Truth
import com.google.common.truth.Truth.assertThat
import com.isranascimento.androidtestutils.TestContextProvider.context
import com.isranascimento.utils.extensions.setVisible
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ViewExtensionsTest {
    private var sut: View? = null

    @Before
    fun setup() {
        sut = View(context())
    }

    @After
    fun tearDown() {
        sut = null
    }

    @Test
    fun `WHEN set visible is called with true THEN it set view visibility to VISIBLE`() {
        sut?.setVisible(true)
        assertThat(sut!!.visibility).isEqualTo(View.VISIBLE)
    }


    @Test
    fun `WHEN set visible is called with false THEN it set view visibility to VISIBLE`() {
        sut?.setVisible(false)
        assertThat(sut!!.visibility).isEqualTo(View.GONE)
    }
}