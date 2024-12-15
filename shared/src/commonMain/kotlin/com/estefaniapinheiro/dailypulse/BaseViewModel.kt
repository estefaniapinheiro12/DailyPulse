package com.estefaniapinheiro.dailypulse

import kotlinx.coroutines.CoroutineScope

//  Classe expect aberta
// Ou seja, outras classes podem estendê-la e mudar seu comportamento
expect open class BaseViewModel (){

    // valor do tipo escopo de corrotina
    /* vamos utlizar esse escopo para iniciar as
    * corrotinas que realização as solicitações HTTP ou as consultas
    * de banco de dados.
    * Para iniciar as corrotinas e começar a fazer trabalho assíncrono
    * precsamos ter um escopo que está sendo executado dentro desse escopo
    * e pode ser cancelado se cancelarmos o próprio escopo
     */
    val scope: CoroutineScope

}