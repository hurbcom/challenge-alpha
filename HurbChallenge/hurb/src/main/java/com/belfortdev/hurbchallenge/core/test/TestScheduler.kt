package com.belfortdev.hurbchallenge.core.test

import android.support.annotation.VisibleForTesting
import com.belfortdev.hurbchallenge.core.network.Scheduler
import io.reactivex.schedulers.Schedulers

@VisibleForTesting(otherwise = VisibleForTesting.NONE)
class TestScheduler : Scheduler {

    override fun mainThread(): io.reactivex.Scheduler {
        return Schedulers.trampoline()
    }

    override fun io(): io.reactivex.Scheduler {
        return Schedulers.trampoline()
    }
}