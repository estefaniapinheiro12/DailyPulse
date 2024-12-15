package com.estefaniapinheiro.dailypulse

import platform.Foundation.NSLog
import platform.UIKit.UIDevice
import platform.UIKit.UIScreen

// vamos construir a classe de plataforma real
actual class Platform () {
    // nome
    actual val osName: String
        get() = UIDevice.currentDevice.systemName
    // versão
    actual val osVersion: String
        get() = UIDevice.currentDevice.systemVersion
    // modelo
    actual val deviceModel: String
        get() = UIDevice.currentDevice.model
    // densidade
    actual val density: Int
        // aqui eu uso a tela da interface do usuário
        get() = UIScreen.mainScreen.scale.toInt()

    actual fun logSystemInfo() {
        // implementação para registrar as informações do sistema
            NSLog(
                "($osName, $osVersion, $deviceModel, $density)"
            )
    }

}