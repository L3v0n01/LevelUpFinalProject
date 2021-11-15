package com.adobe.finalProject.stories

import com.adobe.finalProject.setup.QueryResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class StoriesRepository(private val storiesApiService: StoriesApiService) {

    fun getStories(
        section: Section?,
        apiKey: String
    ): Flow<QueryResult<StoriesResponseModel>> =

        flow {
            if (section == null)  {

                emit(QueryResult.Fail(Exception("No section provided")))
            } else {

                emit(QueryResult.Loading)

                val response = try {

                    QueryResult.Success(storiesApiService.getStories(section.value, apiKey))
                } catch (e: Exception) {

                    QueryResult.Fail(e)
                }

                emit(response)
            }
        }
}
