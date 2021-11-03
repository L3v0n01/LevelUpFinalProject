package com.adobe.finalProject.setup

import com.adobe.finalProject.books.BooksApiService
import com.adobe.finalProject.books.BooksRepository
import com.adobe.finalProject.books.BooksViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.factory
import org.koin.dsl.module
import retrofit2.Retrofit

val viewModelModule = module {
    viewModel<BooksViewModel>()
}

val repositoryModule = module {
    factory<BooksRepository>()
}

val servicesModule = module {
    factory {
        get<Retrofit>().create(BooksApiService::class.java)
    }
}

