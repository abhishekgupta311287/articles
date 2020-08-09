package com.abhishekgupta.articles.di

import androidx.room.Room
import com.abhishekgupta.articles.repository.db.ArticlesDao
import com.abhishekgupta.articles.repository.db.ArticlesDaoImpl
import com.abhishekgupta.articles.repository.db.ArticlesDb
import org.koin.dsl.module

val dbModule = module {
    single {
        Room.databaseBuilder(get(), ArticlesDb::class.java, "articles.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    single<ArticlesDao> { ArticlesDaoImpl((get() as ArticlesDb).articlesDao())}
}