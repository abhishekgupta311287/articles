package com.abhishekgupta.articles.repository.db

import androidx.room.TypeConverter
import com.abhishekgupta.articles.model.Media
import com.abhishekgupta.articles.model.User
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

class ArticleConverter {

    @TypeConverter
    fun fromStringToMedia(value: String?): List<Media> {
        val listType =
            object : TypeToken<ArrayList<Media>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromMediaToString(list: List<Media>): String? {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun fromStringToUser(value: String?): List<User> {
        val listType =
            object : TypeToken<ArrayList<User>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromUserToString(list: List<User>): String? {
        return Gson().toJson(list)
    }
}