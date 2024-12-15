package com.estefaniapinheiro.dailypulse.articles.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// com essa anotação, o Ktor pode serializar a resposta
@Serializable
data class ArticlesResponse (
    // esses dados são da API
    // Dentro desse SerialName eu tenho que colocar o mesmo nome que pede na API
    @SerialName("status")
    val status: String,
    @SerialName("totalResults")
    val results: Int,
    @SerialName("articles")
    // Lista de linha de artigos, que são objetos Json
    val articles: List<ArticleRaw>

    // Isso aqui é a resposta do objeto
    // Está delineando o objeto de resposta externa
)