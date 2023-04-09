package br.com.hurbandroidchallenge.data.remote.data_sources

import br.com.hurbandroidchallenge.data.remote.model.HomeCategoriesResponse
import br.com.hurbandroidchallenge.data.remote.service.StarWarsBookService
import retrofit2.HttpException

class StarWarsBookRemoteDataSourceImpl(
    private val service: StarWarsBookService
) : StarWarsBookRemoteDataSource {

    override suspend fun getHomeCategories(url: String): HomeCategoriesResponse {
        val response = service.getHomeCategories(url = url)
        if (response.isSuccessful) {
            return response.body()!!
        } else {
            throw HttpException(response)
        }
    }

}