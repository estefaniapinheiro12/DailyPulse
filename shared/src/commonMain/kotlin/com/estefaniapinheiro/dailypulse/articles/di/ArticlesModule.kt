package com.estefaniapinheiro.dailypulse.articles.di

import com.estefaniapinheiro.dailypulse.articles.data.ArticlesDataSource
import com.estefaniapinheiro.dailypulse.articles.data.ArticlesRepository
import com.estefaniapinheiro.dailypulse.articles.data.ArticlesService
import com.estefaniapinheiro.dailypulse.articles.application.ArticlesUseCase
import com.estefaniapinheiro.dailypulse.articles.presentation.ArticlesViewModel
import org.koin.dsl.module

// Injetor de todo o recurso do artigo, contêm doas as dependências relacionadas ao artigo

val articlesModule = module {
    // Aqui dentro do corpo é o que vamos instanciar
    // Declaração do escopo da dependência
    // Usaremos o escopo único (singleton), significa que apenas um único objeto será criado para cada
    // dependência
    single<ArticlesService> {
        // Aqui dentro do corpo é incluido o código de instanciação do nosso serviço de artigo(ArtigleService)
        ArticlesService(get())
    }
    // Instância do UseCase
    single <ArticlesUseCase> { ArticlesUseCase(get()) }
    // Instância do ViewModel
    single <ArticlesViewModel>{ ArticlesViewModel(get()) }
    single<ArticlesDataSource>{ ArticlesDataSource(get()) }
    // Instância do ArticlesRepository, aceita dois argumentos e outro para a fonte de dados
    single <ArticlesRepository> { ArticlesRepository(get(), get()) }


}