package com.adobe.finalProject.books

import retrofit2.http.GET
import retrofit2.http.Query


interface BooksApiService {
    @GET("svc/mostpopular/v2/emailed/7.json")
    suspend fun getBooks(@Query("api-key") apikey: String): BooksResponseModel
}