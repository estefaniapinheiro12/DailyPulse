package com.estefaniapinheiro.dailypulse.articles.data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

// Receberá como argumento do construtor um cliente Http que será usado para a executar a solicitação
// GET, o cliente Http, é uma classe que vem da biblioteca principal
class ArticlesService (private val httpClient: HttpClient){
    // valores de configuração
    // categoria do país
    private val country = "us"
    // categoria de negócios, pode ir na API e selecionar a que você prefere
    private val category = "business"
    // chave de API
    private val apiKey = "d0d9b66e114d4aaba3f4b609b1a1fd60"

    // função para buscar arquivos
    suspend fun fetchArticles(): List<ArticleRaw>{
        // Resposta, para obter ela usaremos o cliente Http
        val response: ArticlesResponse = httpClient.get("https://newsapi.org/v2/top-headlines?country=$country&category=$category&apiKey=$apiKey").body()
        return response.articles
    }
}