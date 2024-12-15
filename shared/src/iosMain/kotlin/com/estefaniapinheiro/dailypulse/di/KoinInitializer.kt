package com.estefaniapinheiro.dailypulse.di

import com.estefaniapinheiro.dailypulse.articles.presentation.ArticlesViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin

fun initKoin() {
    // Gráfico de dependências
    val modules = sharedKoinModules + databaseModule

    startKoin {
        // declaração dos modulos acima
        modules(modules)
    }

}
// Fica aqui fora para que seja visível para o app IOS
class ArticlesInjector : KoinComponent {
    val articlesViewModel: ArticlesViewModel by inject()
}