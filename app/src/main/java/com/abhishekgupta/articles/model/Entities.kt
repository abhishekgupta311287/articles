package com.abhishekgupta.articles.model

import androidx.room.Entity

@Entity(tableName = "articles_", primaryKeys = ["id"])
data class Articles(
    val id: Int,
    val createdAt: String? = null,
    val content: String? = null,
    val comments: Int? = null,
    val likes: Int? = null,
    val media: List<Media>? = null,
    val user: List<User>? = null
)

data class Media(
    val id: String? = null,
    val blogId: String? = null,
    val createdAt: String? = null,
    val image: String? = null,
    val title: String? = null,
    val url: String? = null
)

data class User(
    val id: String? = null,
    val blogId: String? = null,
    val createdAt: String? = null,
    val name: String? = null,
    val lastname: String? = null,
    val avatar: String? = null,
    val city: String? = null,
    val designation: String? = null,
    val about: String? = null
)