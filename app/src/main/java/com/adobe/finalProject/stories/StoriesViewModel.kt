package com.adobe.finalProject.stories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adobe.finalProject.setup.QueryResult
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class StoriesViewModel(private val storiesRepository: StoriesRepository) : ViewModel() {

    private val _loadingLiveData = MutableLiveData<Boolean>()
    val loadingLiveData: LiveData<Boolean> = _loadingLiveData
    private val _failLiveData = MutableLiveData<String>()
    val failLiveData: LiveData<String> = _failLiveData

    private val _storiesListLiveData = MutableLiveData<List<StoriesResponseModel.Result>>()
    val storiesListLiveData: LiveData<List<StoriesResponseModel.Result>> = _storiesListLiveData

    private val _section = MutableLiveData<Section>()
    val section: LiveData<Section> = _section

    fun setSection(section: Section) {

        _section.value = section
    }

    fun getStories(apiKey: String) {

        Log.i("ASD", "data ${storiesListLiveData.value}")
        viewModelScope.launch {

            storiesRepository.getStories(_section.value, apiKey).collect {

                when (it) {

                    is QueryResult.Loading -> {
                        _loadingLiveData.value = true
                    }
                    is QueryResult.Success -> {

                        _loadingLiveData.value = false
                        _storiesListLiveData.value = it.response.results
                    }
                    is QueryResult.Fail -> {

                        _loadingLiveData.value = false
                        _failLiveData.value = it.errorMessage.toString()
                    }
                }
            }
        }
    }
}