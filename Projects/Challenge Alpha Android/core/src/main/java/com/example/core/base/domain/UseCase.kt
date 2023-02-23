package com.example.core.base.domain

import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
abstract class UseCase<Response> {
    protected abstract fun performAction(): Response
}