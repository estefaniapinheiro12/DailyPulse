import SwiftUI

// Estendendo a View = algo que exibiremos na tela
struct AboutScreen: View {

     @Environment(\.dismiss)
     private var dismiss

    var body: some View {
    // pilha de navegação  com o título sobre o dispositivo
    // é o que mostraremos como cabeçalho na paret superior da tela
      NavigationStack {
      // A lista que acabamos de criar:
        AboutListView()
          .navigationTitle("About Device")
          // Barra de ferramentas
          // É uma barra de ferramentas para essa tela específica
                         .toolbar {
                         // Terá apenas 1 item
                    ToolbarItem(placement: .primaryAction) {
                    // Quando clicamos no botão, chamaremos o Envioronmental Dimiss
                           Button {
                               dismiss()
                           } label: {
                           // Texto
                               Text("Done")
                                   .bold()
                           }
                       }
                   }
           }
       }
   }

#Preview {
    AboutScreen()
}