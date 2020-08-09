package com.abhishekgupta.articles.repository.network

import com.abhishekgupta.articles.model.Articles
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface IArticlesApi {

    @GET("/jet2/api/v1/blogs")
    fun getArticles(@Query("page")page : Int, @Query("limit") limit: Int) : Single<List<Articles>>
}