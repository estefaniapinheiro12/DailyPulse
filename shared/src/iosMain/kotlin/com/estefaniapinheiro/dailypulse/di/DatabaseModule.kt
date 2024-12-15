package com.estefaniapinheiro.dailypulse.di

import app.cash.sqldelight.db.SqlDriver
import com.estefaniapinheiro.dailypulse.db.DatabaseDriverFactory
import estefaniapinheiro.dailypulse.db.DailyPulseDatabase
import org.koin.dsl.module

// Modúlo do banco de dados
val databaseModule = module {

    single < SqlDriver > {DatabaseDriverFactory().createDriver()}

    single < DailyPulseDatabase > {DailyPulseDatabase(get())}
}