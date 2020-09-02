package com.sendilkumarn.tophnnews.shared.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NewsItem (
    @SerialName("id")
    val id: String,
    @SerialName("by")
    val author: String,
    @SerialName("title")
    val title: String,
    // Well there is no optional in Kotlinx.serialization lib :(
    @SerialName("url")
    val url: String = "I am not an url"
)
