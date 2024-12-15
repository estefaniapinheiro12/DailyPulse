import SwiftUI
import shared

extension ArticlesScreen {

    @MainActor
    // Classe, será um objeto observável, pode ser usado na interface do usuário do Swift
    class ArticlesViewModelWrapper: ObservableObject {
    // Instância de ViewModel de artigos
        let articlesViewModel: ArticlesViewModel


        init() {
        // Duas variavéis instanciadas dentro do bloco init
        // articlesViewModel - solicita que os artigos do setor forneçam uma instância dos artigos
            articlesViewModel = ArticlesInjector ().articlesViewModel
            articlesState = articlesViewModel.articlesState.value
        }
        // Expõe o editor real, artigo do editor indica que a UI vai se inscrever e ouvir
        // Para o fluxo, o editor, o valor inicial que estamos dando, virá do valor do estado do artigo,
        // Esse: articlesState = articlesViewModel.articlesState.value
        @Published var articlesState: ArticlesState

        // chamada por nossa interface do usuário pela tela do artigo
        func startObserving() {
            Task {
            // traduz o fluxo de estado do Kotlin para o editor do IOS para qualquer coisa que esteja,
            // acontecendo dentro do fluxo de estado.
                for await articlesS in articlesViewModel.articlesState {

                    self.articlesState = articlesS
                }
            }
        }
    }
}
// Cada parta da interfcade do usuário deve ser declada como struct e deve ser estendida com View
struct ArticlesScreen: View {

// Semelhante ao fluxo de estado, a interface do usuário sabe como observar esse tipo de fluxo e,
// redesenhaar a tela quando as informações dentro do fluxo mudam ou quando o fluxo está emitindo,
// alguma informação
    @ObservedObject private(set) var viewModel: ArticlesViewModelWrapper

    // Pilha vertical, como uma coluna
    var body: some View {
    // Alinha em ordem vertical
        VStack {
        // Barra de aplicativos
            AppBar()

            // Se for verdadeiro
            if viewModel.articlesState.loading {
            // Exibimos o carregador na tela
                Loader()
            }
            // Se tiver um estado de artigo de erro
            if let error = viewModel.articlesState.error {
            // Exibiremos a mensagem de erro
                ErrorMessage(message: error)
            }
            // Passamos o estado de sucesso real se a lista não estiver vazia.
            if(!viewModel.articlesState.articles.isEmpty) {
            //Pilha de rolagem vertical, desenha os elemntos conforme eles quiserem aparecer na tela
                ScrollView {
                    LazyVStack(spacing: 10) {
                    // Para cada artigo que recebemos no estado do artigo, construimos uma visualização do item do,
                    // artigo.
                        ForEach(viewModel.articlesState.articles, id: \.self) { article in
                            ArticleItemView(article: article)
                        }
                    }
                }
            }
        // função de retorno, chamada quando aparece, quando essa exibição aparece na tela, estamos,
        }.onAppear{
        // chamando o modelo de exibição para que começe a observar
            self.viewModel.startObserving()
        }
    }
}

struct AppBar: View {
    var body: some View {
    // Texto da barra de ferramentas
        Text("Articles")
        // fonte
            .font(.largeTitle)
            // Negrito
            .fontWeight(.bold)
    }
}

struct ArticleItemView: View {
    var article: Article

    var body: some View {
    // Imagem da Web
        VStack(alignment: .leading, spacing: 8) {
            AsyncImage(url: URL(string: article.imageUrl)) { phase in
                // Lidando com os casos de erro, caso não consigamos obeter a imagem da Web
                if phase.image != nil {
                    phase.image!
                        .resizable()
                        .aspectRatio(contentMode: .fit)
                } else if phase.error != nil {
                    Text("Image Load Error")
                } else {
                    ProgressView()
                }
            }
            // Titulo
            Text(article.title)
                .font(.title)
                .fontWeight(.bold)
                // Descrição
            Text(article.desc)
            // Data
            Text(article.date).frame(maxWidth: .infinity, alignment: .trailing).foregroundStyle(.gray)
        }
        .padding(16)
    }
}

struct Loader: View {
    var body: some View {
    // Visualização de progresso, exibi uma barra de progresso
        ProgressView()
    }
}

struct ErrorMessage: View {
    var message: String

    // Recebemos a mensagem de erro
    var body: some View {
    // Exibimos a mensagem de erro na tela
        Text(message)
            .font(.title)
    }
}

