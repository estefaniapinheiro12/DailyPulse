package com.estefaniapinheiro.dailypulse.android.di

// Importa o SqlDriver, que ajuda o app a conversar com o banco de dados.
import app.cash.sqldelight.db.SqlDriver
// Importa o DatabaseDriverFactory, que cria o driver para o banco.
import com.estefaniapinheiro.dailypulse.db.DatabaseDriverFactory
// Importa o DailyPulseDatabase, que é o banco de dados principal do app.
import estefaniapinheiro.dailypulse.db.DailyPulseDatabase
// Importa o Koin para usar o contexto do Android.
import org.koin.android.ext.koin.androidContext
// Importa o módulo do Koin, que ajuda a organizar as coisas.
import org.koin.dsl.module

// Cria uma caixinha chamada databaseModule para guardar coisas do banco de dados.
val databaseModule = module {

    // Diz ao Koin como criar um SqlDriver.
    // Usa o DatabaseDriverFactory e o contexto do app.
    single < SqlDriver > { DatabaseDriverFactory(androidContext()).createDriver() }
    // Diz ao Koin como criar o DailyPulseDatabase.
    // O "get()" pede o SqlDriver que já foi configurado antes.
    single < DailyPulseDatabase > {DailyPulseDatabase(get())}
}