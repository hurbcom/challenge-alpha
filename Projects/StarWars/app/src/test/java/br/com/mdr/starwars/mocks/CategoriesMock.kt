package br.com.mdr.starwars.mocks

import br.com.mdr.starwars.base.REQUEST_ERROR
import br.com.mdr.starwars.domain.model.Category
import br.com.mdr.starwars.domain.model.PageState
import br.com.mdr.starwars.extension.SerializationExtension.jsonToObject
import br.com.mdr.starwars.extension.getJsonFromAssetsOrResources
import kotlinx.coroutines.flow.flow
import java.net.ConnectException

val categories: PageState<List<Category>> = PageState.Success(getCategories())

val emptyCategories: PageState<List<Category>> = PageState.Empty

val mockedException = ConnectException(REQUEST_ERROR)

val errorCategory = PageState.Error(mockedException)

private fun getCategories(): List<Category> {
    return getJsonFromAssetsOrResources("categories-list.json")
        .jsonToObject<List<Category>>() ?: emptyList()
}

fun emit(value: PageState<List<Category>>) = flow {
    this.emit(value)
}