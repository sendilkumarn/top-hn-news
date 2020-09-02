package com.sendilkumarn.tophnnews.shared.network

import com.sendilkumarn.tophnnews.shared.entity.NewsItem
import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*

class HNClient {
    private val httpClient = HttpClient {
        install(JsonFeature) {
            val json = kotlinx.serialization.json.Json {
                ignoreUnknownKeys = true
                isLenient = true
            }
            serializer = KotlinxSerializer(json)
        }
    }

    private suspend fun getTopStories(): List<Long> {
        return httpClient.get(TOP_STORIES)
    }

    private suspend fun getStory(url: String): NewsItem {
        return httpClient.get(url)
    }

    suspend fun getStories(): List<NewsItem> {
        return getTopStories().take(20).map {
            val url = getURL(it)
            var newsItem = getStory(url)
            if (newsItem.url == NOT_AN_URL) {
                NewsItem(newsItem.id, newsItem.author, newsItem.title, url)
            } else {
                newsItem
            }
        }
    }

    private fun getURL(id: Long) = "${STORY}${id}.json?print=pretty"

    companion object {
        private const val TOP_STORIES = "https://hacker-news.firebaseio.com/v0/topstories.json?print=pretty"
        private const val STORY = "https://hacker-news.firebaseio.com/v0/item/"
        private const val NOT_AN_URL = "I am not an URL"
    }
}