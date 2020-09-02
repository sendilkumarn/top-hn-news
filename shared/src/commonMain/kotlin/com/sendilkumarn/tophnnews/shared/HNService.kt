package com.sendilkumarn.tophnnews.shared

import com.sendilkumarn.tophnnews.shared.entity.NewsItem
import com.sendilkumarn.tophnnews.shared.network.HNClient

class HNService {
    private val client = HNClient()

    @Throws(Exception::class)
    suspend fun getNews(): List<NewsItem> {
        return client.getStories()
    }
}