package br.com.hurbandroidchallenge.data.remote.data_sources

import br.com.hurbandroidchallenge.data.remote.model.FilmDto
import br.com.hurbandroidchallenge.data.remote.model.HomeCategoriesDto
import br.com.hurbandroidchallenge.data.remote.model.PeopleDto
import br.com.hurbandroidchallenge.data.remote.model.base.PagedListResponse
import br.com.hurbandroidchallenge.data.remote.service.StarWarsBookService
import retrofit2.HttpException

class StarWarsBookRemoteDataSourceImpl(
    private val service: StarWarsBookService
) : StarWarsBookRemoteDataSource {

    override suspend fun getHomeCategories(): HomeCategoriesDto {
        val response = service.getHomeCategories()
        if (response.isSuccessful) {
            return response.body()!!
        } else {
            throw HttpException(response)
        }
    }

    override suspend fun getCharacters(url: String): PagedListResponse<PeopleDto> {
        val response = service.getCharacters(url)
        if (response.isSuccessful) {
            return response.body()!!
        } else {
            throw HttpException(response)
        }
    }

    override suspend fun getFilms(url: String): PagedListResponse<FilmDto> {
        val response = service.getFilms(url)
        if (response.isSuccessful) {
            return response.body()!!
        } else {
            throw HttpException(response)
        }
    }

}