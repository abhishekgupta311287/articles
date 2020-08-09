package com.abhishekgupta.articles

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.abhishekgupta.articles.view.adapter.ArticlesAdapter
import com.abhishekgupta.articles.viewmodel.ArticlesViewModel
import kotlinx.android.synthetic.main.activity_articles.*
import org.koin.android.ext.android.get

class ArticlesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_articles)

        articlesView.layoutManager = LinearLayoutManager(this)
        articlesView.setHasFixedSize(true)

        val adapter = ArticlesAdapter()
        articlesView.adapter = adapter

        val viewModel:ArticlesViewModel = get()
        viewModel
            .fetchArticles(1,10)
            .observe(this, Observer {
                adapter.articles = it
            })

    }
}
