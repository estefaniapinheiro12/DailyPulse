package com.estefaniapinheiro.dailypulse.db

import app.cash.sqldelight.db.SqlDriver

// Expect Class = Android e Ios devem incluir a implementação real
expect class DatabaseDriverFactory {

    fun createDriver(): SqlDriver
}