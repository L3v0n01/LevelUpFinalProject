package com.adobe.finalProject.setup

import com.adobe.finalProject.books.BooksApiService
import com.adobe.finalProject.books.BooksRepository
import com.adobe.finalProject.books.BooksViewModel
import com.adobe.finalProject.stories.StoriesApiService
import com.adobe.finalProject.stories.StoriesRepository
import com.adobe.finalProject.stories.StoriesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.factory
import org.koin.dsl.module
import retrofit2.Retrofit

val viewModelModule = module {
    viewModel<BooksViewModel>()
    viewModel<StoriesViewModel>()
}

val repositoryModule = module {
    factory<BooksRepository>()
    factory<StoriesRepository>()
}

val servicesModule = module {
    factory {
        get<Retrofit>().create(BooksApiService::class.java)
    }
    factory {
        get<Retrofit>().create(StoriesApiService::class.java)
    }
}

