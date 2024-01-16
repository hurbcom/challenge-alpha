package com.vdemelo.starwarswiki.data.api

interface StarWarsApi {

    // Request example
    // https://api.github.com/search/repositories?q=language:kotlin&sort=stars&page=1

//    @GET("search/repositories")
//    suspend fun searchGithub(
//        @Query("q") query: String = "kotlin",
//        @Query("sort") sort: String = "stars",
//        @Query("page") page: Int = 1,
//        @Query("per_page") perPage: Int = PAGING_PAGE_SIZE
//    ): SearchResponse

//    companion object {
//        fun create(): GithubApi {
//            val logger = HttpLoggingInterceptor { Timber.d("API $it") }
//            logger.level = HttpLoggingInterceptor.Level.BASIC
//
//            val client = OkHttpClient.Builder()
//                .addInterceptor(logger)
//                .build()
//            return Retrofit.Builder()
//                .baseUrl(BASE_URL)
//                .client(client)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build()
//                .create(GithubApi::class.java)
//        }
//    }
}
