package br.com.hurbandroidchallenge.data.repository

import br.com.hurbandroidchallenge.commom.extension.pagedListOf
import br.com.hurbandroidchallenge.commom.mapper.Mapper
import br.com.hurbandroidchallenge.commom.mapper.NullableListMapper
import br.com.hurbandroidchallenge.data.local.data_source.StarWarsBookLocalDataSource
import br.com.hurbandroidchallenge.data.local.model.HomeCategoriesEntity
import br.com.hurbandroidchallenge.data.local.preferences.PreferencesWrapper
import br.com.hurbandroidchallenge.data.remote.data_sources.StarWarsBookRemoteDataSource
import br.com.hurbandroidchallenge.data.remote.model.HomeCategoriesDto
import br.com.hurbandroidchallenge.data.remote.util.NetworkManager
import br.com.hurbandroidchallenge.data.remote.util.apiCall
import br.com.hurbandroidchallenge.domain.model.Categories
import br.com.hurbandroidchallenge.domain.model.base.PagedList
import br.com.hurbandroidchallenge.domain.repository.StarWarsBookRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class CategoriesRepository(
    private val remoteDataSource: StarWarsBookRemoteDataSource,
    private val localDataSource: StarWarsBookLocalDataSource,
    private val homeCategoriesEntityToCategoriesMapper: NullableListMapper<HomeCategoriesEntity, Categories>,
    private val homeCategoriesDtoToEntityMapper: Mapper<HomeCategoriesDto, List<HomeCategoriesEntity>>,
    private val networkManager: NetworkManager,
) : StarWarsBookRepository<Categories> {

    private val preferences = PreferencesWrapper.getInstance()

    private suspend fun updateLocalCategories() {
        val remoteCategories = remoteDataSource.getHomeCategories()
        localDataSource.updateHomeCategories(
            categories = homeCategoriesDtoToEntityMapper.map(remoteCategories)
        )
    }

    private suspend fun getLocalCategories() =
        homeCategoriesEntityToCategoriesMapper.map(localDataSource.getHomeCategories())

    override fun getItemList(url: String): Flow<PagedList<Categories>> {
        return flow {
            if (preferences.isCategoriesUpToDate()) {
                emit(pagedListOf(getLocalCategories()))
            } else if (networkManager.hasInternetConnection()) {
                apiCall {
                    updateLocalCategories()
                    emit(pagedListOf(getLocalCategories()))
                }
            } else {
                emit(pagedListOf(getLocalCategories()))
            }
        }
    }

    override fun getItemByUrl(url: String): Flow<Categories> {
        return flow { }
    }

}