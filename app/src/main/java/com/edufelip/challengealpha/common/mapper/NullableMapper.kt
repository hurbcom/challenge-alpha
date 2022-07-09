package com.edufelip.challengealpha.common.mapper

interface NullableMapper<I, O> {
    fun map(input: I?): O?
}