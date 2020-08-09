package com.abhishekgupta.articles.repository.db

import com.abhishekgupta.articles.model.Articles
import io.reactivex.Single

class ArticlesDaoImpl(private val dao: ArticlesDBDao) : ArticlesDao {
    override fun getAllArticles(): Single<List<Articles>> = dao.getAllArticles()

    override fun insertArticles(articles: List<Articles>) = dao.insert(articles)
}