package com.abhishekgupta.articles.repository.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.abhishekgupta.articles.model.Articles

@Database(entities = [Articles::class], version = 1, exportSchema = false)
@TypeConverters(ArticleConverter::class)
abstract class ArticlesDb : RoomDatabase() {

    abstract fun articlesDao(): ArticlesDBDao
}