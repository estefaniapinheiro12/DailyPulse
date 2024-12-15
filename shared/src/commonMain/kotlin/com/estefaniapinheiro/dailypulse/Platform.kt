package com.estefaniapinheiro.dailypulse

// interface é semelhante ao protocolo em Swift
// ela não vai ser mais uma interface, sim uma expect class
// aqui nessa classe não é permitido nenhum tipo de implementação dentro da função
// ela não tem permisão nem para inicializar seus próprios valores
// a implementação real dessa classe, deve resisdir nas pastas do Android e Ios
expect class Platform {
    //nome do sistema Operacional
    val osName: String
    // versão do sistema operacional
    val osVersion: String
    // modelo do dispositivo
    val deviceModel: String
    // densidade
    val density: Int
   // essa função registra todas as informações
    fun logSystemInfo()
}

