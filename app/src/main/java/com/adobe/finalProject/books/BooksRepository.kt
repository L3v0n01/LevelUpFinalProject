package com.adobe.finalProject.books

import com.adobe.finalProject.setup.QueryResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class BooksRepository(
    private val booksApiService: BooksApiService
) {
    fun getBooksList(apiKey: String): Flow<QueryResult<BooksResponseModel>> =
        flow {
            emit(QueryResult.Loading)
            val response =
                try {
                    QueryResult.Success(booksApiService.getBooks(apiKey))
                } catch (e: Exception) {
                    QueryResult.Fail(e)
                }
            emit(response)
        }
}

