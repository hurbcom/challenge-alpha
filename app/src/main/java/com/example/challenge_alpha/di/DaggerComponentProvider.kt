package com.example.challenge_alpha.di

import androidx.fragment.app.Fragment

interface DaggerComponentProvider {

    val component: AppComponent
}

val Fragment.injector get() = (activity?.application as DaggerComponentProvider).component
