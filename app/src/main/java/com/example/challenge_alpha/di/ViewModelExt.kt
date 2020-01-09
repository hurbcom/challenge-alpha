package com.example.challenge_alpha.di

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


    inline fun <reified T : ViewModel> viewModel(fragment: Fragment, crossinline initializer: () -> T) =
        lazy(LazyThreadSafetyMode.NONE) { fragment.getViewModel { initializer() } }

    inline fun <reified T : ViewModel> Fragment.getViewModel(crossinline factory: () -> T) = T::class.java.let {
        ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>) = factory() as T
        }).get(it)
    }

