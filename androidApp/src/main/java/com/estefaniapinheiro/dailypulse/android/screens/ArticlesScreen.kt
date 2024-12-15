package com.estefaniapinheiro.dailypulse.android.screens


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.estefaniapinheiro.dailypulse.articles.application.Article
import com.estefaniapinheiro.dailypulse.articles.presentation.ArticlesViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshState
import org.koin.androidx.compose.getViewModel

// implementação da tela


// fun com Composable, começam com letra maíscula
@Composable
fun ArticlesScreen(
    // Retorno de chamada, que será chamado quando clicarmos no botão "Sobre"
    onAboutButtonClick: () -> Unit,
    // Requer informações do modelo de visualização dos artigos
    articlesViewModel: ArticlesViewModel = getViewModel(),
) {
    // articlesState vem do ArticlesViewModel
    // collectAsState = coleta o fluxo de estado.
    val articlesState = articlesViewModel.articlesState.collectAsState()

    // Coluna vertical
    Column {
        // Barra de aplicativos
        AppBar(onAboutButtonClick)
        // Se o erro não for nulo, exibiremos uma mensagem de erro
        if (articlesState.value.error != null)
        // A mensagem vem diretamente do Modelo de Visualização (pode ser do backend ou pela lógica do app)
            ErrorMessage(articlesState.value.error!!)
        // Se os artigos não estiverem vazios, construiremos uma exibição de uma lista de artigos
        if (articlesState.value.articles.isNotEmpty())
        // Exibir o modo de exibição da lista de arquivos, como argumento, passamos os artigos do fluxo
            ArticlesListView(articlesViewModel)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
// Cria a barra de aplicativos superior
private fun AppBar(
    // Botão "Sobre"
    onAboutButtonClick: () -> Unit,
) {
    TopAppBar(
        // Título
        title = { Text(text = "Articles") },
        actions = {
            // Botão de ícone, quando clicado, chama o retorno de chamada que recebemos como argumento,
            // o "onAboutButtonClick"
            IconButton(onClick = onAboutButtonClick) {
                Icon(
                    imageVector = Icons.Outlined.Info,
                    contentDescription = "About Device Button",
                )
            }
        }
    )
}

@Composable
fun ArticlesListView(viewModel: ArticlesViewModel) {

    // Pull refresh
    SwipeRefresh(
        // estado para saber quando exibirá a atualização na tela
        state = SwipeRefreshState(viewModel.articlesState.value.loading),
        onRefresh = { viewModel.getArticles(true) }) {
        // Coluna preguiçosa, calcula a exibição à medida que rolamos a tela
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            // Passamos o artigo que estamos recebendo, para cada artigo estamos construindo a visualização,
            // do item do artigo
            items(viewModel.articlesState.value.articles) { article ->
                ArticleItemView(article = article)
            }
        }

    }
}

@Composable
fun ArticleItemView(article: Article) {
    // Coluna
    // Constroi um par de artigo alinhado verticalmente
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        //Imagem carredana da Web, usando uma biblioteca de terceiros
        AsyncImage(
            model = article.imageUrl,
            contentDescription = null
        )
        // Pequeno espaço, de modo que as exibições não fiquem empilhadas
        Spacer(modifier = Modifier.height(4.dp))
        // Título
        Text(
            text = article.title,
            // Estilo do título
            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 22.sp)
        )
        // Espaçador
        Spacer(modifier = Modifier.height(8.dp))
        // Descrição do artigo
        Text(text = article.desc)
        // Espaçador
        Spacer(modifier = Modifier.height(4.dp))
        // Data do artigo
        Text(
            text = article.date,
            // Cor cinza
            style = TextStyle(color = Color.Gray),
            // Alinhamento será na extremidadedo final
            modifier = Modifier.align(Alignment.End)
        )
        Spacer(modifier = Modifier.height(4.dp))
    }
}

@Composable
// message: String = recebendo a mensagem como entrada
fun ErrorMessage(message: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        // Exibição de texto
        Text(
            text = message,
            // Estilo do texto
            style = TextStyle(fontSize = 28.sp, textAlign = TextAlign.Center)
        )
    }
}
