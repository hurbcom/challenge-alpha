package com.barreto.android.presentation.base

import androidx.annotation.CallSuper
import androidx.databinding.PropertyChangeRegistry
import androidx.lifecycle.ViewModel
import com.barreto.android.domain.util.ISchedulerProvider
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel(
    protected val scheduler: ISchedulerProvider
) : ViewModel() {

    @Transient
    private var mCallbacks: PropertyChangeRegistry? = null

    protected val disposables = CompositeDisposable()


    @CallSuper
    override fun onCleared() {
        disposables.clear()
    }
}