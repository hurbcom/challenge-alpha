package com.example.core.base

import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
abstract class UseCase<Response> {
    protected abstract fun performAction(): Response
}