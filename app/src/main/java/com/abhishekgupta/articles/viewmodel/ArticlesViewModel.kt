package com.abhishekgupta.articles.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.abhishekgupta.articles.model.Articles
import com.abhishekgupta.articles.repository.network.ArticlesRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ArticlesViewModel(private val repository: ArticlesRepository) : ViewModel() {

    private val disposable: CompositeDisposable = CompositeDisposable()
    private val articlesLiveData: MutableLiveData<List<Articles>> = MutableLiveData()

    fun fetchArticles(page: Int, limit: Int):LiveData<List<Articles>> {
        disposable.add(
            repository.getArticles(page, limit)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { articlesLiveData.postValue(it as ArrayList<Articles>) }
                    , { error -> Log.e("", error.message, error) }
                )
        )
        return articlesLiveData
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}