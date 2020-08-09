package com.abhishekgupta.articles.repository.network

import com.abhishekgupta.articles.model.Articles
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class ArticlesRepository(private val api: IArticlesApi) {

    fun getArticles(page: Int, limit: Int): Single<List<Articles>> {
        return api.getArticles(page, limit)
            .subscribeOn(Schedulers.io())
            .flatMap {
                Single.just(it)
            }
            .onErrorReturn {
                emptyList()
            }
    }

}