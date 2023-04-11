package com.example.challengealphaandroid.api

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.Query
import com.apollographql.apollo3.exception.ApolloException
import com.example.challengealphaandroid.GetPeopleQuery
import com.example.challengealphaandroid.GetPlanetsQuery
import com.example.challengealphaandroid.GetStarshipsQuery
import com.example.challengealphaandroid.model.Result

interface IApolloClient {
    suspend fun <T : Query.Data> fetchList(query: Query<T>): Result<List<T>>
}

class RealApolloClient(private val apolloClient: ApolloClient) : IApolloClient {
    override suspend fun <T : Query.Data> fetchList(query: Query<T>): Result<List<T>> {
        return try {
            val response = apolloClient.query(query).execute()
            val data = response.data
            if (data == null) {
                Result.Error("Response data is null")
            } else {
                val list = when (data) {
                    is GetPeopleQuery.Data -> data.allPeople?.people as List<T>
                    is GetStarshipsQuery.Data -> data.allStarships?.starships as List<T>
                    is GetPlanetsQuery.Data -> data.allPlanets?.planets as List<T>
                    else -> emptyList()
                }
                Result.Success(list)
            }
        } catch (e: ApolloException) {
            Result.Error(e.message ?: "Unknown error")
        } catch (e: Exception) {
            Result.Error("Unknown error: ${e.message}")
        }
    }
}