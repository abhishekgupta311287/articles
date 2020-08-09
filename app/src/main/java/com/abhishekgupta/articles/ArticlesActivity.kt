package com.abhishekgupta.articles

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.abhishekgupta.articles.view.adapter.ArticlesAdapter
import com.abhishekgupta.articles.viewmodel.ArticlesViewModel
import kotlinx.android.synthetic.main.activity_articles.*
import org.koin.android.ext.android.get

class ArticlesActivity : AppCompatActivity() {

    private var currentPage = 1
    private var isPaginating = false

    private val adapter by lazy { ArticlesAdapter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_articles)

        articlesView.layoutManager = LinearLayoutManager(this)
        articlesView.setHasFixedSize(true)

        articlesView.adapter = adapter

        fetchArticles(currentPage)

        articlesView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(
                recyclerView: RecyclerView,
                dx: Int, dy: Int
            ) {
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val itemCount = layoutManager.itemCount
                val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()

                if (!isPaginating && itemCount <= lastVisibleItemPosition + THRESHOLD) {
                    adapter.articles.add(null)
                    adapter.notifyItemInserted(adapter.articles.size - 1)
                    fetchArticles(currentPage)
                }
            }
        })
    }

    private fun fetchArticles(page: Int) {
        isPaginating = true
        val viewModel: ArticlesViewModel = get()
        viewModel
            .fetchArticles(page, ARTICLES_LIMIT)
            .observe(this, Observer {
                if (adapter.articles.remove(null)) {
                    adapter.notifyItemRemoved(adapter.articles.size)
                }

                adapter.articles.addAll(it)
                adapter.notifyDataSetChanged()
                currentPage++
                isPaginating = false

            })
    }

    companion object {
        private const val THRESHOLD = 3
        private const val ARTICLES_LIMIT = 10
    }
}
