package com.hotelurbano.desafio.util

import com.hotelurbano.desafio.base.util.SchedulerProvider
import io.reactivex.Scheduler
import io.reactivex.schedulers.TestScheduler

/**
 * Created by Cristian on 17/12/2017.
 */
class TestSchedulerProvider constructor(private val testScheduler: TestScheduler) : SchedulerProvider {
    override fun ui(): Scheduler = testScheduler
    override fun computation(): Scheduler = testScheduler
    override fun io(): Scheduler = testScheduler
}