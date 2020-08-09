package com.abhishekgupta.articles.view.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.abhishekgupta.articles.R
import com.abhishekgupta.articles.common.format
import com.abhishekgupta.articles.common.formatStringDate
import com.abhishekgupta.articles.model.Articles
import com.bumptech.glide.Glide

class ArticlesAdapter :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var articles: List<Articles> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = View.inflate(parent.context, R.layout.content_articles, null)

        return ArticleViewHolder(view)
    }

    override fun getItemCount() = articles.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ArticleViewHolder) {
            val article = articles[position]
            val media = article.media?.get(0)
            val user = article.user?.get(0)

            user?.let {
                holder.userName.text = "${user.name} ${user.lastname}"
                holder.userDesignation.text = user.designation

                Glide
                    .with(holder.profilePic)
                    .load(user.avatar)
                    .into(holder.profilePic)
            }

            media?.let {
                holder.articleTitle.text = media.title?.capitalize()
                holder.articleUrl.text = media.url

                holder.articleImage.visibility =View.VISIBLE

                    if (media.image.isNullOrEmpty()) {
                        View.GONE
                    } else {
                        Glide
                            .with(holder.articleImage)
                            .load(media.image)
                            .into(holder.articleImage)

                        View.VISIBLE
                    }

            }

            holder.articleContent.text = article.content?.capitalize()
            holder.likes.text = article.likes?.toLong()?.format()
            holder.comments.text = article.comments?.toLong()?.format()
            holder.createdTime.text = article.createdAt?.formatStringDate()

        }
    }

    inner class ArticleViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val createdTime: TextView = view.findViewById(R.id.createdTime)
        val userName: TextView = view.findViewById(R.id.userName)
        val userDesignation: TextView = view.findViewById(R.id.userDesignation)
        val profilePic: ImageView = view.findViewById(R.id.profilePic)
        val articleImage: ImageView = view.findViewById(R.id.articleImage)
        val articleContent: TextView = view.findViewById(R.id.articleContent)
        val articleTitle: TextView = view.findViewById(R.id.articleTitle)
        val articleUrl: TextView = view.findViewById(R.id.articleUrl)
        val likes: TextView = view.findViewById(R.id.likes)
        val comments: TextView = view.findViewById(R.id.comments)
    }
}