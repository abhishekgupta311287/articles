package com.abhishekgupta.articles

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.abhishekgupta.articles.view.adapter.ArticlesAdapter
import kotlinx.android.synthetic.main.activity_articles.*

class ArticlesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_articles)

        articlesView.layoutManager = LinearLayoutManager(this)
        articlesView.setHasFixedSize(true)
        articlesView.adapter = ArticlesAdapter()

    }
}
