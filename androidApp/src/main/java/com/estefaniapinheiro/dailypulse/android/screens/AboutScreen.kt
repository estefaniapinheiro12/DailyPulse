package com.estefaniapinheiro.dailypulse.android.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.estefaniapinheiro.dailypulse.Platform

// Tudo que é marcado com Composable, é algo que quero que o usuário veja
@Composable
// Essa função cria a tela "Sobre o Dispositivo".
fun AboutScreen(
    // Quando clicamos no botão de voltar, fazemos algo com essa função.
    // Unit = não retorna nada, apenas vai voltar para a tela quando o botão for clicado
    // onUpButtonClick = está sendo passada como argumento, mas está construida em AppScaffold
    // onUpButtonClick = usada como callback, função que você define fora do AboutScreen e passa para ele.
    onUpButtonClick: () -> Unit
) {
    // Cria uma coluna, que é como uma pilha de coisas, uma embaixo da outra.
    // normalmente usada para uma lista menor
    Column {
        // barra de ferramentas
        // normalmente fica na parte superior de cada aplicativo
        // as vezes contém a navegação, e um título que indica a finalidade da tela
        // Adiciona a barra no topo com título e botão de voltar.
        Toolbar(onUpButtonClick)
        // Adiciona o conteúdo da tela (a lista de informações).
        ContentView()
    }
}
// Esse código diz que estamos usando algo experimental do Material3.Material3: É a biblioteca que
// cuida da aparência do app, como botões, barras e cores.
//ExperimentalMaterial3Api: Significa que essa funcionalidade do Material3 ainda está em fase de
// testes, então pode mudar no futuro.
// @OptIn: Serve para "optar por usar" algo experimental e avisar que você está ciente disso.
@OptIn(ExperimentalMaterial3Api::class)
@Composable
// Essa função cria a barra que fica no topo da tela.
private fun Toolbar(
    onUpButtonClick: () -> Unit
) {
    // TopAppBar é como a "faixa superior" da tela, que tem título e botão.
    TopAppBar(
        // Coloca o texto "About Device" como título da barra.
        title = { Text(text = "About Device") },
        // Adiciona um botão para voltar.
        navigationIcon = {
            // ícone de navegação, quando clicado, chama a função de retorno de chamada, essa: onUpButtonClick,
            // Que usamos como argumento
            IconButton(onClick = onUpButtonClick) {
                Icon(
                    // O ícone é uma seta para trás.
                    imageVector = Icons.Default.ArrowBack,
                    // usada para acessibilidade. Ela fornece uma descrição de texto para o ícone
                    // (no caso, a seta para voltar).
                    contentDescription = "Up Button",
                )
            }
        }
    )
}
// a visualização do conteúdo
@Composable
private fun ContentView() {
    // itens exibidos na tela
    val items = makeItems()

    // criação da lista na interface do usuário
    // essa coluna calcula apenas os elemenos que serão exibidos na tela
    LazyColumn(
        // Indica que a lista ocupará o máximo de espaço possível
        modifier = Modifier.fillMaxSize(),
    ) {
        // criando as exibições de linha para a lista
        items(items) { row ->
            RowView(title = row.first, subtitle = row.second)
        }
    }
}
// como não tem Composbale, significa que ela está retornando uma lista
private fun makeItems(): List<Pair<String, String>> {
    // aqui estamos criando um objeto a partir da classe plataforma
    val platform = Platform()
    // também estou chamando a função, para depurar qualquer coisa que esteja acontecendo
    platform.logSystemInfo()

    // estamos devolvendo uma lista
    return listOf(
        // aqui tem 3 pares
        // Sistema operacional, dispositivo, densidade
        Pair("Operating System", "${platform.osName} ${platform.osVersion}"),
        Pair("Device", platform.deviceModel),
        Pair("Density", platform.density.toString())
    )
}

@Composable
private fun RowView(
    // argumentos: título, subtítulo
    title: String,
    subtitle: String,
) {
    // retorna uma coluna com 8 dp de preenchimento
    Column(Modifier.padding(8.dp)) {
        // título ou o rótulo
        Text(
            text = title,
            // pequeno corpo, onde está sendo repassado tudo que recebemos como título
            style = MaterialTheme.typography.bodySmall,
            // cor cinza
            color = Color.Gray,
        )
        // valor que está saindo da nossa classe de plataforma
        Text(
            // usamos ele como subtítulo
            text = subtitle,
            // peso mais encorpado
            style = MaterialTheme.typography.bodyLarge,
        )
    }
    // usado para que a lista apareça com um divisor entre cada item lista
    Divider()
}