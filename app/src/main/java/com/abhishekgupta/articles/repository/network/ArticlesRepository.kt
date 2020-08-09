package com.abhishekgupta.articles.repository.network

import com.abhishekgupta.articles.model.Articles
import com.abhishekgupta.articles.repository.db.ArticlesDao
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class ArticlesRepository(
    private val api: IArticlesApi,
    private val articlesDao: ArticlesDao
) {

    fun getArticles(page: Int, limit: Int): Single<List<Articles>> {
        return api.getArticles(page, limit)
            .subscribeOn(Schedulers.io())
            .flatMap {
                articlesDao.insertArticles(it)
                Single.just(it)
            }
            .onErrorReturn {
                ArrayList()
            }
    }

}