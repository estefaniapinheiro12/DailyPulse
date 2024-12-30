package com.estefaniapinheiro.dailypulse.android.di

import app.cash.sqldelight.db.SqlDriver
import com.estefaniapinheiro.dailypulse.db.DatabaseDriverFactory
import estefaniapinheiro.dailypulse.db.DailyPulseDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single<SqlDriver> { DatabaseDriverFactory(androidContext()).createDriver() }
    single<DailyPulseDatabase> { DailyPulseDatabase(get()) }
}