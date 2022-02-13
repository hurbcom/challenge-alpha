package com.isranascimento.androidtestutils

import android.content.Context
import org.robolectric.RuntimeEnvironment

object TestContextProvider {
    fun context(): Context = RuntimeEnvironment.getApplication()
}