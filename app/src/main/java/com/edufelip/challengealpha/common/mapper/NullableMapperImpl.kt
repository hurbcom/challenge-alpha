package com.edufelip.challengealpha.common.mapper

class NullableMapperImpl<I, O>(
    private val mapper: Mapper<I, O>
) : NullableMapper<I, O> {
    override fun map(input: I?): O? {
        return if (input == null) {
            null
        } else {
            mapper.map(input)
        }
    }
}