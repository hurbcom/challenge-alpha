package com.isranascimento.challengealpha.di

import android.content.Context
import com.isranascimento.hotelslist.repository.HotelsListRepository
import io.mockk.mockkClass
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.koin.test.KoinTest
import org.koin.test.check.checkKoinModules
import org.koin.test.mock.MockProviderRule
import kotlin.reflect.KClass

class KoinUtilsTest: KoinTest {

    @get:Rule
    val mockProvider = MockProviderRule.create { kClass: KClass<*> ->
        mockkClass(kClass)
    }

    @Test
    fun `verify dependency graph with debug true`() {
        checkKoinModules(KoinUtils.getModules(true, "https://google.com/"))
    }

    @Test
    fun `verify dependency graph with debug false`() {
        checkKoinModules(KoinUtils.getModules(false, "https://google.com/"))
    }

}