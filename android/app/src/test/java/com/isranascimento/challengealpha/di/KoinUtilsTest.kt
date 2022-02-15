package com.isranascimento.challengealpha.di

import android.content.Context
import androidx.test.runner.AndroidJUnit4
import com.isranascimento.androidtestutils.TestContextProvider.context
import io.mockk.mockkClass
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.koinApplication
import org.koin.test.KoinTest
import org.koin.test.check.checkKoinModules
import org.koin.test.check.checkModules
import org.koin.test.mock.MockProviderRule
import kotlin.reflect.KClass

@RunWith(AndroidJUnit4::class)
class KoinUtilsTest: KoinTest {

    @get:Rule
    val mockProvider = MockProviderRule.create { kClass: KClass<*> ->
        mockkClass(kClass)
    }

    @Test
    fun `verify dependency graph with debug true`() {
        koinApplication {
            androidContext(context())
            modules(KoinUtils.getModules(true, "https://google.com/"))
            checkModules()
        }
    }

    @Test
    fun `verify dependency graph with debug false`() {
        koinApplication {
            androidContext(context())
            modules(KoinUtils.getModules(false, "https://google.com/"))
            checkModules()
        }
    }

}