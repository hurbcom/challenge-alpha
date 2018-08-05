package com.github.felipehjcosta.huchallenge

import com.github.felipehjcosta.huchallenge.base.MainApplication
import com.github.felipehjcosta.huchallenge.base.di.ApplicationComponent
import com.github.felipehjcosta.huchallenge.di.DaggerTestApplicationComponent
import com.github.felipehjcosta.huchallenge.di.TestApplicationComponent

class MockMainApplication : MainApplication() {

    val applicationComponent: TestApplicationComponent = DaggerTestApplicationComponent.create()

    override fun createComponent(): ApplicationComponent {
        return applicationComponent
    }
}