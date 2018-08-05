package com.github.felipehjcosta.huchallenge

import android.app.Application
import android.content.Context
import android.os.Bundle
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnitRunner
import com.linkedin.android.testbutler.TestButler
import com.squareup.rx2.idler.Rx2Idler
import io.reactivex.plugins.RxJavaPlugins

class HUChallengeAndroidJUnitRunner : AndroidJUnitRunner() {

    override fun newApplication(cl: ClassLoader?, className: String?, context: Context?): Application {
        return super.newApplication(cl, MockMainApplication::class.java.name, context)
    }

    override fun onStart() {
        TestButler.setup(InstrumentationRegistry.getTargetContext())
        RxJavaPlugins.setInitNewThreadSchedulerHandler(Rx2Idler.create("New Thread Scheduler"))
        super.onStart()
    }

    override fun finish(resultCode: Int, results: Bundle) {
        TestButler.teardown(InstrumentationRegistry.getTargetContext())
        super.finish(resultCode, results)
    }
}