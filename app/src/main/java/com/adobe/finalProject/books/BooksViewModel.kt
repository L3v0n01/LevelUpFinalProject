package com.adobe.finalProject.books

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adobe.finalProject.setup.QueryResult
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class BooksViewModel(
    private val booksRepository: BooksRepository
) : ViewModel() {
    private val _loadingLiveData = MutableLiveData<Boolean>()
    val loadingLiveData: LiveData<Boolean> = _loadingLiveData
    private val _failLiveData = MutableLiveData<String>()
    val failLiveData: LiveData<String> = _failLiveData
    private val _booksListLiveData = MutableLiveData<BooksResponseModel.Results>()
    val booksListLiveData: LiveData<BooksResponseModel.Results> = _booksListLiveData

    fun getBooksList(apiKey: String) {
        viewModelScope.launch {
            booksRepository.getBooksList(apiKey).collect {
                when (it) {
                    is QueryResult.Loading -> _loadingLiveData.value = true
                    is QueryResult.Success -> {
                        _booksListLiveData.value = it.response.results
                        _loadingLiveData.value = false
                    }
                    is QueryResult.Fail ->{
                        _failLiveData.value = it.errorMessage.toString()
                        _loadingLiveData.value = false
                    }
                }

            }
        }
    }

}