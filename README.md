# App de Notícias

Um aplicativo desenvolvido com Kotlin Multiplatform (KMP), utilizando Kotlin e Swift.Este projeto foi criado com o objetivo de aprofundar os conhecimentos em KMP e explorar seu potencial no desenvolvimento de aplicativos multiplataforma. O app é uma solução moderna e robusta para a exibição de notícias em dispositivos Android e iOS.

## Funcionalidades
- **Exibição de notícias**: Interface simples e intuitiva.
- **Multiplataforma**: Código compartilhado entre Android e iOS, garantindo consistência e eficiência.

## Tecnologias e Padrões Utilizados

- **Clean Architecture**: Estrutura modular e escalável para o código.
- **MVI (Model-View-Intent)**: Padrão para gerenciamento de estado e interações do usuário.
- **Ktor**: Biblioteca para chamadas de API.
- **SQLDelight**: Solução para persistência de dados multiplataforma.
- **Koin**: Injeção de dependência simples e eficaz.
- **Jetpack Compose**: Framework para criação de interfaces declarativas no Android.
- **SwiftUI**: Ferramenta moderna para desenvolvimento de interfaces no iOS.

## Estrutura do Projeto
O projeto está organizado em módulos para separar responsabilidades e permitir fácil manutenção:

- **shared**: Código compartilhado entre as plataformas.
- **androidApp**: Código específico para Android.
- **iosApp**: Código específico para iOS.

## Como Executar

### Requisitos
- **Android Studio**: Para desenvolvimento e teste no Android.
- **Xcode**: Para desenvolvimento e teste no iOS.

### Passos

1. Clone o repositório:
   ```bash
   git clone <https://github.com/estefaniapinheiro12/DailyPulse>
   ```

2. Abra o projeto no Android Studio e/ou Xcode.

3. Execute os aplicativos:
   - **Android**: Configure um dispositivo/emulador e clique em "Run".
   - **iOS**: Configure um simulador/dispositivo e clique em "Run" no Xcode.

![DailyPulse](https://github.com/user-attachments/assets/2b64505b-1b33-41dc-b727-dfa75d7518ac)


## Contribuição
Contribuições são bem-vindas! Sinta-se à vontade para abrir issues e pull requests para melhorias.

## Licença
[Apache License 2.0](LICENSE)

---
Desenvolvido por Estefânia Pinheiro: https://github.com/estefaniapinheiro12
