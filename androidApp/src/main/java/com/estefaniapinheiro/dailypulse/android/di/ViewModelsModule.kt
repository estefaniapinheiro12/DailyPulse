package com.estefaniapinheiro.dailypulse.android.di

import com.estefaniapinheiro.dailypulse.articles.presentation.ArticlesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {

    viewModel {
        ArticlesViewModel(get())
    }
}
