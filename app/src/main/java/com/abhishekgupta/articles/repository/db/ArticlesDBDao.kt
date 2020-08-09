package com.abhishekgupta.articles.repository.db

import androidx.room.*
import com.abhishekgupta.articles.model.Articles
import io.reactivex.Single

@Dao
interface ArticlesDBDao {

    @Query("SELECT * FROM articles_ ORDER BY id ASC")
    fun getAllArticles(): Single<List<Articles>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(article: List<Articles>)

}