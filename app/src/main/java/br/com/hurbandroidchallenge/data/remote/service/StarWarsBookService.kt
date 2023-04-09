package br.com.hurbandroidchallenge.data.remote.service

import br.com.hurbandroidchallenge.data.remote.model.HomeCategoriesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface StarWarsBookService {

    @GET
    suspend fun getHomeCategories(@Url url: String): Response<HomeCategoriesResponse>

}