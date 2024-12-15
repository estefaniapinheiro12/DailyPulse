package com.estefaniapinheiro.dailypulse.db

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import estefaniapinheiro.dailypulse.db.DailyPulseDatabase

// No lado do Android, vamos adicionar um constructor adiciona, que é o contexto do Android
// Context = contém informações sobre o dispositivo e sobre o estado atual do nosso app
actual class DatabaseDriverFactory (private val context: Context){

    // implementação da expect class DatabaseDriverFactory
    actual fun createDriver(): SqlDriver =
        // Driver SQL do Android
        AndroidSqliteDriver (
            // o esquema será extraído do banco de dados, que é ponto do esquema
            schema = DailyPulseDatabase.Schema,
            // passamos o contexto, já temos ele no constructor
            context = context,
            name = "DailyPulse.Database.db"

        )
}