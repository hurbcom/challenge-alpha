package com.belfortdev.hurbchallenge.core.network

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AppScheduler : Scheduler {

    override fun mainThread(): io.reactivex.Scheduler {
        return AndroidSchedulers.mainThread()
    }

    override fun io(): io.reactivex.Scheduler {
        return Schedulers.io()
    }
}