package com.estefaniapinheiro.dailypulse

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.cancel

actual open class BaseViewModel {

    //escopo de corrotina
    // Aqui criaremos manualemnte um escopo assíncrono
    // Aqui significa que todo trabalho será instanciado nesse escopo,
    // Não será executado no thread principal, mas sim, em um thread de entrada e saída,
    // Normalmente, é isso que queremos fazer quando estamos fazendo solicitações HTTP,
    // para um back-end ou quando estamos consultando um banco de dados
    actual val scope: CoroutineScope = CoroutineScope(Dispatchers.IO)

    // função criada manualmente para limpar esse escopo e cancelar todo o trabalho
    // Quando usamos o mecanismo "expect" nossa obrigação é criar implementações reais,
    // para todas as implementações esperadas, mas não é é proibido adicionar mais funções e mais,
    // valores do que classe esperada contém.
    fun clear (){
        // cancelando o escopo acima
        // No Ios, devemos sempre lembrar de chamar essa função,
        //Quando esse trebalho que o ViewModel está realizando não for necessário,
        // Acontece quando nossas visualizações estão sendo destruídas
        scope.cancel()
    }

}