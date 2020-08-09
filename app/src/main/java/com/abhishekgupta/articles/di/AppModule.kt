package com.abhishekgupta.articles.di

import com.abhishekgupta.articles.repository.network.ArticlesRepository
import com.abhishekgupta.articles.repository.network.IArticlesApi
import com.abhishekgupta.articles.viewmodel.ArticlesViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    single {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val builder = OkHttpClient.Builder()
        builder.addInterceptor(interceptor)
        val okHttpClient = builder.build()
        val factory = RxJava2CallAdapterFactory.create()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://5e99a9b1bc561b0016af3540.mockapi.io")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(factory)
            .build()

        val api = retrofit.create(IArticlesApi::class.java)
        api
    }
    single { ArticlesRepository(get()) }

    viewModel { ArticlesViewModel(get()) }
}