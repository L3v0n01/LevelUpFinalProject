package com.adobe.finalProject.setup


sealed class QueryResult<out T> {

    object Loading : QueryResult<Nothing>()

    data class Success<out T>(val response: T) : QueryResult<T>()

    data class Fail<out T>(val errorMessage: Exception) : QueryResult<T>()
}