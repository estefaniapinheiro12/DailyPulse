package com.estefaniapinheiro.dailypulse.android.di

// Importa o ArticlesViewModel, que é o cérebro para lidar com artigos.
import com.estefaniapinheiro.dailypulse.articles.presentation.ArticlesViewModel
// Importa ferramentas do Koin para trabalhar com ViewModels no Android.
import org.koin.androidx.viewmodel.dsl.viewModel
// Importa o módulo do Koin, que é como uma caixinha para organizar coisas.
import org.koin.dsl.module

// Cria uma caixinha chamada viewModelsModule para guardar ViewModels.
val viewModelsModule = module {
    // Module = usado para organizar e registrar as dependências que o aplicativo precisa. É como
    // criar uma lista de itens que o Koin vai saber como fabricar ou entregar quando o aplicativo pedir.

    // Diz ao Koin como criar um ArticlesViewModel.
    viewModel {
        // viewModel = Registra um ViewModel.
        // Cria um ArticlesViewModel. O "get()" ajuda o Koin a pegar o que ele precisa.
        // O get() é como dizer: "Koin, busque as coisas necessárias para criar isso!"
        ArticlesViewModel(get())
    }
}

// Este arquivo diz ao Koin como criar e gerenciar o ArticlesViewModel.
// Ele ajuda a organizar e facilitar o uso do ViewModel no app.
// Se comunica com: ArticlesViewModel, Koin