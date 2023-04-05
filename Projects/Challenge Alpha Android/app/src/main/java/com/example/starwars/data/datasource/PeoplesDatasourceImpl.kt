package com.example.starwars.data.datasource

import android.net.Uri
import com.example.starwars.data.ApiRequest
import com.example.starwars.data.model.PeoplePage
import com.example.starwars.retrofit.ApiResult
import com.example.starwars.retrofit.apiResultTransform
import kotlinx.coroutines.flow.Flow

class PeoplesDatasourceImpl(private val apiRequest: ApiRequest) : PeoplesDatasource {
    override fun getPeoples(page: String): Flow<ApiResult<PeoplePage>> {
        return apiRequest.getPeoples(page).apiResultTransform {
            it.apply {
                this?.nextPage = this?.nextPage?.let {
                    Uri.parse(this.nextPage).getQueryParameter("page") ?: "0"
                } ?: run { "0" }
                this?.previousPage = this?.previousPage?.let {
                    Uri.parse(this.previousPage).getQueryParameter("page") ?: "0"
                } ?: run { "0" }
            }
        }
    }
}
