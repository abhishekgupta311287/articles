package com.abhishekgupta.articles.repository.db

import com.abhishekgupta.articles.model.Articles
import io.reactivex.Single

interface ArticlesDao {

    fun getAllArticles(): Single<List<Articles>>

    fun insertArticles(articles: List<Articles>)

}