package com.estefaniapinheiro.dailypulse.articles.presentation

import com.estefaniapinheiro.dailypulse.BaseViewModel
import com.estefaniapinheiro.dailypulse.articles.application.ArticlesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


// Classe que gerencia e emite o estado dos artigos para a interface do usuário
class ArticlesViewModel(
    private val useCase: ArticlesUseCase
) : BaseViewModel() {

    // Vamos usar programação reativa com fluxos (flows) para facilitar a comunicação
    // entre o ViewModel e a interface do usuário.
    // No contexto das corrotinas, os fluxos em Kotlin são chamados de flows.
    // Aqui, declaramos uma variável do tipo StateFlow para emitir o estado dos artigos,
    // como estado de carregamento, sucesso ou erro.
    // Estamos inicializando o MutableStateFlow, que precisa de um estado inicial obrigatório.
    private val _articlesState: MutableStateFlow<ArticlesState> =
        MutableStateFlow(ArticlesState(loading = true))
    // O loading, significa que o estado está começando com um carregamento
    // Revisando a linha acima:
    // articlesState será um fluxo de informações entre o ViewModel e a interface do usuário.
    // O fluxo manterá o estado dos artigos, podendo indicar sucesso, erro ou carregamento.
    // e as informações serão transmitidas, e o Article State, deve mostrar um resultado bem-sucedido,
    // Nosso fluxo mutável é privado para impedir que outras classes modifiquem o estado diretamente.
    //Obs: ter um fluxo de estado público mutável não é bom, pois qualquer pessoa pode adicionar informações ao nosso fluxo,
    // por isso nossa variavél está privada

    // Fluxo de estado público e imutável, acessível externamente sem permitir modificações,
    // garantindo que somente o ViewModel controle o fluxo.
    // assim que podemos proteger os resultados que nosso viewModel vai expor
    val articlesState: StateFlow<ArticlesState> get() = _articlesState


    //Tudo o que colocarmos no bloco init será executado quando o ViewModel for instanciado.
    init {
        getArticles()
    }

    // Função que vai simular a recuperação de artigos
    fun getArticles(forceFetch: Boolean = false) {
        // Escopo de corrotina que reside no BaseViewModel para iniciar uma nova corrotina
        scope.launch {

            // Mostra que a atualização foi realizada na tela
            _articlesState.emit(ArticlesState(loading = true, articles = _articlesState.value.articles))

            // simulação de uma busca do backend, como vem de uma função suspend, ele vai esperar,
            // até que seja concluido sem bloquear o thread principal
            val fetchedArticles = useCase.getArticles(forceFetch)

            // Aqui, podemos executar código assíncrono sem bloquear a thread principal
            // Emite um estado simulado para articlesState com base nos artigos que buscamos
            // Aqui emitidos o estado de sucesso
            _articlesState.emit(ArticlesState(articles = fetchedArticles))

        }

    }

}
// Resumo:
// Quando a interface do usuário instancia o ArticlesViewModel, ela se inscreve automaticamente no fluxo
// de articlesState, recebendo atualizações sempre que o estado mudar.
// No bloco init, chamamos getArticles para iniciar o carregamento de dados.
// A função getArticles inicia uma nova corrotina e simula o carregamento de artigos

// Resumo
// MutableStateFlow = gerencia e emiti valores que podem mudar ao longo do tempo,