package com.estefaniapinheiro.dailypulse

import android.content.res.Resources
import android.os.Build
import android.util.Log
import kotlin.math.round

// aqui vamos declarar e instanciar todos os valores esperados
actual class Platform () {
    actual val osName: String
        get() = "Android"
    actual val osVersion: String
        /*aqui construi uma string e usei a istancia construida para obter informações
        reais da versão do Sistema Operacional
         */
        get() = "${Build.VERSION.SDK_INT}"
    actual val deviceModel: String
        // aqui funciona como a val de cima
        get() = "${Build.MANUFACTURER} ${Build.MODEL}"
    actual val density: Int
        // estamos obtendo dos recursos do sistema
        // obtemos as métricas de exibição e a delas obtemos a densidade
        // e nos certificamos que ela esteja no formato inteiro
        get() = round(Resources.getSystem().displayMetrics.density).toInt()

    actual fun logSystemInfo() {
        // aqui vamos adicionar a implementação para registrar as informações
        Log.d(
            "Daily Pulse",
            // aqui é a mensagem de registro real
            "($osName, $osVersion, $deviceModel, $density)"

        )
    }

}
// aqui todo esse código é específico da plataforma Android