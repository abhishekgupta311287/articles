package com.abhishekgupta.articles.view.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abhishekgupta.articles.R
import com.abhishekgupta.articles.common.format
import com.abhishekgupta.articles.common.formatStringDate
import com.abhishekgupta.articles.model.Articles
import com.bumptech.glide.Glide

class ArticlesAdapter(private val context: Context) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var articles: ArrayList<Articles?> = ArrayList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return when (viewType) {
            ARTICLES_VIEW -> {
                val view = View.inflate(parent.context, R.layout.content_articles, null)
                ArticleViewHolder(view)
            }
            PAGINATION_VIEW -> {
                val view = View.inflate(parent.context, R.layout.pagination_indicator, null)
                PaginationViewHolder(view)
            }
            else -> {
                IllegalView(null)
            }
        }


    }

    override fun getItemCount() = articles.size

    override fun getItemViewType(position: Int): Int {
        return if (articles[position] == null) {
            PAGINATION_VIEW
        } else {
            ARTICLES_VIEW
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ArticleViewHolder && articles.isNotEmpty()) {
            val article = articles[position]
            val user = article?.user?.get(0)

            user?.let {
                holder.userName.text =
                    context.getString(R.string.user_name, user.name, user.lastname)
                holder.userDesignation.text = user.designation

                Glide
                    .with(holder.profilePic)
                    .load(user.avatar)
                    .into(holder.profilePic)
            }

            if (!article?.media.isNullOrEmpty()) {
                val media = article?.media?.get(0)

                media?.let {
                    holder.articleTitle.text = media.title?.capitalize()
                    holder.articleUrl.text = media.url

                    holder.articleTitle.visibility = View.VISIBLE
                    holder.articleUrl.visibility = View.VISIBLE
                    holder.articleImage.visibility = View.VISIBLE

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

            } else {
                holder.articleTitle.visibility = View.GONE
                holder.articleUrl.visibility = View.GONE
                holder.articleImage.visibility = View.GONE
            }
            holder.articleContent.text = article?.content?.capitalize()
            holder.likes.text =
                context.getString(R.string.likes, article?.likes?.toLong()?.format())
            holder.comments.text =
                context.getString(R.string.comments, article?.comments?.toLong()?.format())
            holder.createdTime.text = article?.createdAt?.formatStringDate()

        } else if (holder is PaginationViewHolder) {
            holder.paginationIndicator.isIndeterminate = true
        }
    }

    companion object {
        const val ARTICLES_VIEW: Int = 0
        const val PAGINATION_VIEW: Int = 1
    }
}