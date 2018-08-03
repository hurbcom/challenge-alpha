package com.github.felipehjcosta.huchallenge.base.hotels

import io.reactivex.observers.TestObserver
import org.junit.Test

class MemoryHotelsRepositoryTest {

    @Test
    fun ensureFetchHotelsReturnsStub() {
        val itemObserver = TestObserver.create<List<String>>()
        MemoryHotelsRepository().fetchHotels().subscribe(itemObserver)

        itemObserver.awaitTerminalEvent()

        itemObserver.assertValue { it == listOf("Hotel Vilamar Copacabana") }

        itemObserver.dispose()
    }
}