package br.com.hurbandroidchallenge.data.mapper.films

import br.com.hurbandroidchallenge.commom.mapper.Mapper
import br.com.hurbandroidchallenge.data.local.model.FilmEntity
import br.com.hurbandroidchallenge.data.remote.model.FilmDto

class FilmDtoToEntityMapper : Mapper<FilmDto, FilmEntity> {
    override fun map(input: FilmDto) = input.toEntity()
}