package com.hotelurbano.desafio.base.util

import io.reactivex.Scheduler

/**
 * Created by Cristian on 17/12/2017.
 */
interface SchedulerProvider {
    fun ui(): Scheduler
    fun computation(): Scheduler
    fun io(): Scheduler
}