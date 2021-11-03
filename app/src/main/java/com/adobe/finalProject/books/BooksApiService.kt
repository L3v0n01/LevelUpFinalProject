package com.adobe.finalProject.books

import retrofit2.http.GET
import retrofit2.http.Query


interface BooksApiService {
    @GET("svc/books/v3/lists/overview.json")
    suspend fun getBooks(@Query("api-key") apikey: String): BooksResponseModel
}