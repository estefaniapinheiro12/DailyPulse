package com.estefaniapinheiro.dailypulse

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

// essa classe não contém nenuma lógica,
// mas os recursos reais serão estendidos a partir,
// dessa classe e adicionárão a lógica que desejarem.
// No lado do Android, estenderá o modelo de visualização do Android(ViewModel)
actual open class BaseViewModel: ViewModel () {

    // valor real, o escopo do modelo de visualização
    // Queremos isso, pois o ViewModel do Android,
    // bem como o escopo do ViewModel, são sensíveis ao ciclo de vida,
    //significa que eles percebem quando a interface do usuário é destruída,
    // Quando a interface és destruida, esse escopo também é destruído
    actual val scope = viewModelScope

}