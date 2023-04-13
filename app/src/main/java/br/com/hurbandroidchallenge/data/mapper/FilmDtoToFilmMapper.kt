package br.com.hurbandroidchallenge.data.mapper

import br.com.hurbandroidchallenge.commom.mapper.Mapper
import br.com.hurbandroidchallenge.data.local.model.FilmEntity
import br.com.hurbandroidchallenge.data.remote.model.FilmDto
import br.com.hurbandroidchallenge.domain.model.Film

class FilmDtoToEntityMapper : Mapper<FilmDto, FilmEntity> {
    override fun map(input: FilmDto) = input.toEntity()
}