package com.estefaniapinheiro.dailypulse.articles.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArticleRaw (
    // dados que constam na API
    @SerialName("title")
    val title: String,
    @SerialName("description")
    // declarada como anulável pois nem todos artigos tem descrição
    val desc: String?,
    @SerialName("publishedAt")
    val date: String,
    @SerialName("urlToImage")
    // Anulável pois nem todos artigos tem imagens
    val imageUrl: String?
)
