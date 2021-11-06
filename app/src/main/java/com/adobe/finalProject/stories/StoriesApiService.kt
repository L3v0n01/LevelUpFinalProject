package com.adobe.finalProject.stories

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface StoriesApiService {

    @GET("svc/topstories/v2/{section}.json")
    suspend fun getStories(
        @Path("section") section: String,
        @Query("api-key") apikey: String
    ): StoriesResponseModel
}