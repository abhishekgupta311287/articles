package com.abhishekgupta.articles.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.abhishekgupta.articles.common.isNetworkAvailable
import com.abhishekgupta.articles.model.Articles
import com.abhishekgupta.articles.repository.db.ArticlesDao
import com.abhishekgupta.articles.repository.network.ArticlesRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ArticlesViewModel(
    private val context: Context,
    private val repository: ArticlesRepository,
    private val articlesDao: ArticlesDao
) : ViewModel() {

    private val disposable: CompositeDisposable = CompositeDisposable()
    private val articlesLiveData: MutableLiveData<List<Articles>> = MutableLiveData()

    fun fetchArticles(page: Int, limit: Int): LiveData<List<Articles>> {
        if (context.isNetworkAvailable() == true) {
            fetchArticlesFromApi(page, limit)
        } else {
            fetchArticlesFromDb()
        }
        return articlesLiveData

    }

    private fun fetchArticlesFromApi(page: Int, limit: Int) {
        disposable.add(
            repository.getArticles(page, limit)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        articlesLiveData.postValue(it as ArrayList<Articles>)
                    },
                    { error ->
                        Log.e("ArticlesViewModel", error.message, error)
                        fetchArticlesFromDb()
                    }
                )
        )
    }

    private fun fetchArticlesFromDb() {
        disposable.add(
            articlesDao
                .getAllArticles()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { articles ->
                    articlesLiveData.postValue(articles)
                }
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}