package br.com.hurbandroidchallenge.commom.mapper

interface Mapper<I, O> {
    fun map(input: I): O
}