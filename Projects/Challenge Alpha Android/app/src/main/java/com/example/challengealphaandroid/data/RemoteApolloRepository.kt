package com.example.challengealphaandroid.data

import com.apollographql.apollo3.api.Query
import com.example.challengealphaandroid.api.IApolloClient
import com.example.challengealphaandroid.model.Result
import javax.inject.Inject

class RemoteApolloRepository @Inject constructor(private val apolloClient: IApolloClient) {
    suspend fun <T : Query.Data> fetchList(query: Query<T>): Result<List<T>> = apolloClient.fetchList(query)
}

