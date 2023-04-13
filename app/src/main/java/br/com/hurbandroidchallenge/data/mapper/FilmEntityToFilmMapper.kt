package br.com.hurbandroidchallenge.data.mapper

import br.com.hurbandroidchallenge.commom.mapper.Mapper
import br.com.hurbandroidchallenge.data.local.model.FilmEntity
import br.com.hurbandroidchallenge.data.remote.model.FilmDto
import br.com.hurbandroidchallenge.domain.model.Film

class FilmEntityToFilmMapper : Mapper<FilmEntity, Film> {
    override fun map(input: FilmEntity) = input.toFilm()
}