package com.abhishekgupta.articles.view.adapter

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.abhishekgupta.articles.R

class ArticleViewHolder(view: View) : RecyclerView.ViewHolder(view) {
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

class PaginationViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val paginationIndicator: ProgressBar = itemView.findViewById(R.id.paginationIndicator)
}

/**
 * Invalid View . Ideally this should never be loaded
 */
class IllegalView(view: View?) : RecyclerView.ViewHolder(view!!) {
}