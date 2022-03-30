package com.example.cleanarchitecturerandompics.di

import com.example.cleanarchitecturerandompics.fragment.PhotoListViewModel
import com.example.data.api.repository.PhotoRepositoryImpl
import com.example.data.api.service.PhotoService
import com.example.domain.repository.PhotoRepository
import com.example.domain.usecase.GetPhotoUseCase
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel

val repositoryModules = module {
    single(named("remote")) {
        PhotoRepositoryImpl(get(named(API))) as PhotoRepository
    }
}

val useCaseModules = module {
    factory(named(GET_NEWS_USECASE)) {
        GetPhotoUseCase(photoRepository = get(named("remote")))
    }
}

val networkModules = module {
    single(named(OKHTTP_INSTANCE)) { createOkHttpClient() }
    single(named(API)) { createWebService<PhotoService>(get(named(OKHTTP_INSTANCE)), BASE_URL) }
}

val viewModels = module {
    viewModel{
        PhotoListViewModel(get(named(GET_NEWS_USECASE)))
    }
}


private const val BASE_URL = "https://api.unsplash.com/"
private const val OKHTTP_INSTANCE = "OkHttp"
private const val API = "Api"
private const val GET_NEWS_USECASE = "getNewsUseCase"