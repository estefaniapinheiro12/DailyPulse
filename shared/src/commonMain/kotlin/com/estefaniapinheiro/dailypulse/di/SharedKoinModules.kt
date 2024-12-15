package com.estefaniapinheiro.dailypulse.di

import com.estefaniapinheiro.dailypulse.articles.di.articlesModule

// Aqui contêm todos os modulos que são compartilhados entre Android e Ios

val sharedKoinModules = listOf(
    // Aqui são os módulos compartilhados
    articlesModule,
    networdkModule
)