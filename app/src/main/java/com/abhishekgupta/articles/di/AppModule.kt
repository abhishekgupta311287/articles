package com.abhishekgupta.articles.di

import com.abhishekgupta.articles.repository.network.ArticlesRepository
import com.abhishekgupta.articles.viewmodel.ArticlesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { ArticlesRepository(get(), get()) }
    viewModel { ArticlesViewModel(get(), get(), get()) }
}