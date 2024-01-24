package com.br.myapplication.data.repository.favorites

import com.br.myapplication.data.model.Favorite

interface IFavoritesRepository {

    suspend fun getFavoritesList() : List<Favorite>


}