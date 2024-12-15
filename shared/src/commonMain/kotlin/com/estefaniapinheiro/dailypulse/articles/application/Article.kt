package com.estefaniapinheiro.dailypulse.articles.application

// Classe de dados, temos apenas dados, nada de comportamentos
data class Article (
    // Título
    val title: String,
    // Descrição
    val desc: String,
    // Data
    val date: String,
    // Url da imagem
    val imageUrl: String

)
// aqui será nossa entidade, como os artigos estão organizados