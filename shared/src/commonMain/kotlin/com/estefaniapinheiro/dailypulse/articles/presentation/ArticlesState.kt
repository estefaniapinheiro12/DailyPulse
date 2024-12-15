package com.estefaniapinheiro.dailypulse.articles.presentation

import com.estefaniapinheiro.dailypulse.articles.application.Article

// classe de dados
// Aqui vamos declarar os estados dos artigos
data class ArticlesState(

    // Sucesso, aqui tem uma lista de artigos,
    val articles: List<Article> = listOf(),
    // Carregamento, será exibido antes de recuperarmos o resultado do backend
    val loading: Boolean = false,
    //Erro, pode ser nulo, se nada deu errado
    val error: String? = null
    )
