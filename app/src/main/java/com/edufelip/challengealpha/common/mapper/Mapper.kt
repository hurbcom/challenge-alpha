package com.edufelip.challengealpha.common.mapper

interface Mapper<I, O> {
    fun map(input: I): O
}