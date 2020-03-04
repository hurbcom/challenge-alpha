package com.hurb.challengealpha.idlingresource

import androidx.test.espresso.IdlingResource
import java.util.concurrent.atomic.AtomicBoolean

//Communicates between activity and testing when resources are ready to be tested
class SimpleIdlingResource : IdlingResource {
    @Volatile
    private var mCallback: IdlingResource.ResourceCallback? = null
    private val mIsIdleNow =
        AtomicBoolean(false)

    override fun getName(): String {
        return this.javaClass.name
    }

    //Called to verify if is idle and available for testing
    override fun isIdleNow(): Boolean {
        return mIsIdleNow.get()
    }

    override fun registerIdleTransitionCallback(callback: IdlingResource.ResourceCallback) {
        mCallback = callback
    }

    //Sets idle state (false when loading and true when resources are ready)
    fun setIdleState(isIdleNow: Boolean) {
        mIsIdleNow.set(isIdleNow)
        if (isIdleNow && mCallback != null) {
            mCallback!!.onTransitionToIdle()
        }
    }
}