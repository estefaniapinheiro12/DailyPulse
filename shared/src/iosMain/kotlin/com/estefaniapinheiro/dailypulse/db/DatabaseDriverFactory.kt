package com.estefaniapinheiro.dailypulse.db

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import estefaniapinheiro.dailypulse.db.DailyPulseDatabase

actual class DatabaseDriverFactory () {

    // função que tem expect class DatabaseDriverFactory
    actual fun createDriver(): SqlDriver =
        NativeSqliteDriver(
            schema = DailyPulseDatabase.Schema,
            name = "DailyPulseDatabase.db"
        )
}