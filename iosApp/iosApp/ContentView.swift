import SwiftUI
import shared

struct ContentView: View {

// Variavél de estado, o SwiftUI, está sempre monitorando e redesenha a tela sempre que seu valor,
// muda, o valor é false, pois não queremos abrir a tela de informações na inicialização do app
@State private var shouldOpenAbout = false
     let articlesScreen = ArticlesScreen(viewModel: .init())

	var body: some View {
	// retorna o corpo e a pilha de navegação
	NavigationStack{
	// Tela do artigo
	ArticlesScreen(viewModel: .init())
        // Barra de ferramentas
	    .toolbar {
	    //Item
	    ToolbarItem {
	    // Botão
	            Button {
	            // Quando clicamos no botão, o valor muda para verdadeiro
                    shouldOpenAbout = true
                    // Rótulo
	            } label: {
	            // Terá uma imagem do sistema, título e ícone
	                Label ("About", systemImage: "info.circle").labelStyle(.titleAndIcon)
	            }
	            // Quando o valor é verdadeiro, estamos mostrando a tela sobre
	            .popover (isPresented: $shouldOpenAbout) {
	                    AboutScreen()
	                }
	            }
	        }
        }.refreshable {
            articlesScreen.viewModel.articlesViewModel.getArticles(forceFetch: true)
        }
    }
}
struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}