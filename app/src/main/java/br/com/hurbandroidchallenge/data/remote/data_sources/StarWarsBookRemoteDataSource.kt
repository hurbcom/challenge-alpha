package br.com.hurbandroidchallenge.data.remote.data_sources

import br.com.hurbandroidchallenge.data.remote.config.ApiUrls
import br.com.hurbandroidchallenge.data.remote.model.HomeCategoriesResponse

interface StarWarsBookRemoteDataSource {

    suspend fun getHomeCategories(url: String = ApiUrls.baseUrl): HomeCategoriesResponse

}