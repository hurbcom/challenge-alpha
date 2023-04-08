package com.example.hurbandroidchallenge.commom.mapper

interface Mapper<I, O> {
    fun map(input: I): O
}