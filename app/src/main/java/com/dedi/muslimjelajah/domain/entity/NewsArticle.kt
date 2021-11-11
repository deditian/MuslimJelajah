package com.dedi.muslimjelajah.domain.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news_articles")
data class NewsArticle(
    val title: String?,
    @PrimaryKey
    @ColumnInfo(name = "url")
    val url: String,
    val thumbnailUrl: String?,
    val isBookmarked: Boolean,
    val updatedAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "breaking_news")
data class BreakingNews(
    @ColumnInfo(name = "articleUrl")
    val articleUrl: String,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
)