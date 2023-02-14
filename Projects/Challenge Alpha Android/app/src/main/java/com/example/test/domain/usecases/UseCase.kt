package com.example.test.domain.usecases

import com.example.core.base.BaseResponse
import com.example.core.base.UseCaseWithParams
import com.example.test.domain.models.Data
import com.example.test.domain.repositories.Repository
import com.example.test.domain.usecases.params.GetParams
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UseCase @Inject constructor(
    private val repository: Repository
) : UseCaseWithParams<GetParams, Flow<BaseResponse<Data>>>() {
    override fun performAction(requestData: GetParams): Flow<BaseResponse<Data>> {

    }
}