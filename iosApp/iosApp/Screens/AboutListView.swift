// importando o módulo compartilhado
// para poder usar a classe da plataforma
import shared
import SwiftUI

// structs estendem as exibições (Views)
// significa que essa estrutura é uma parte da interface do usuário
struct AboutListView: View {
// item da linha, contém o título e o subtítulo
// pode ver que não tem visualização (View), ou seja, não é algo que será exibido na tela
// ele apenas contém as informações que serão exibidas na tela
  private struct RowItem: Hashable {
    let title: String
    let subtitle: String
  }

// inicializando as informações que esses itens conterão
  private let items: [RowItem] = {
  // instancia do objeto da classe plataforma
    let platform = Platform()
    // chamamos a função de informações
    platform.logSystemInfo()

// lista de itens de linha
// assim que declara uma lista no Swift
    var result: [RowItem] = [
    // inicializando a lista
      .init(
        title: "Operating System",
        // construindo os valores reais
        subtitle: "\(platform.osName) \(platform.osVersion)"
      ),
      .init(
        title: "Device",
        subtitle: platform.deviceModel
      ),
      .init(
        title: "Density",
        subtitle: "@\(platform.density)x"
      )
    ]
    return result
  }()
// corpo da lista
// Estendendo View: exibido na tela
  var body: some View {
  // lista de interface do usuário com os elementos que criamos anteriormente
    List {
      ForEach(items, id: \.self) { item in
      // VStack = semelhante a column do compose
        VStack(alignment: .leading) {
        // Título
          Text(item.title)
            .font(.footnote)
            .foregroundStyle(.secondary)
            // subtítulo
          Text(item.subtitle)
            .font(.body)
            .foregroundStyle(.primary)
        }
        .padding(.vertical, 4)
      }
    }
  }
}

#Preview {
    AboutListView()
}