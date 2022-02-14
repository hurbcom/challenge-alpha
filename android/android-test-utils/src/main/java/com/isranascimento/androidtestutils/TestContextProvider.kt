package com.isranascimento.androidtestutils

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import org.robolectric.RuntimeEnvironment

object TestContextProvider {
    fun context(): Context = ApplicationProvider.getApplicationContext()
}