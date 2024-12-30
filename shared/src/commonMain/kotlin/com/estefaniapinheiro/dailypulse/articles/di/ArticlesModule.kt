package com.estefaniapinheiro.dailypulse.articles.di

import com.estefaniapinheiro.dailypulse.articles.data.ArticlesDataSource
import com.estefaniapinheiro.dailypulse.articles.data.ArticlesRepository
import com.estefaniapinheiro.dailypulse.articles.data.ArticlesService
import com.estefaniapinheiro.dailypulse.articles.application.ArticlesUseCase
import com.estefaniapinheiro.dailypulse.articles.presentation.ArticlesViewModel
import org.koin.dsl.module

val articlesModule = module {
    single<ArticlesService> {
        ArticlesService(get())
    }
    single<ArticlesUseCase> { ArticlesUseCase(get()) }
    single<ArticlesViewModel> { ArticlesViewModel(get()) }
    single<ArticlesDataSource> { ArticlesDataSource(get()) }
    single<ArticlesRepository> { ArticlesRepository(get(), get()) }

}