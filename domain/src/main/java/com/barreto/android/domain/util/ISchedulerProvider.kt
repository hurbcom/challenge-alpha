package com.barreto.android.domain.util

import io.reactivex.Scheduler

interface ISchedulerProvider {

    fun mainThread():Scheduler
    fun backgroundThread():Scheduler
}