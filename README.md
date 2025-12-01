# CineInfo

CineInfo é uma aplicação Android moderna construída com **Jetpack Compose** que permite aos usuários explorar filmes populares, pesquisar por títulos específicos e visualizar informações detalhadas. O app segue a arquitetura **MVVM** e utiliza **Room** para cache local e **Retrofit** para requisições de rede.

## Funcionalidades

-   **Feed Inicial**: Navegue por uma lista de filmes populares obtidos da API do TMDB.
-   **Pesquisa**: Busque por filmes pelo título.
-   **Detalhes do Filme**: Visualize detalhes completos, incluindo sinopse, data de lançamento e avaliação.
-   **Suporte Offline**: Filmes populares são armazenados localmente usando Room, permitindo acesso mesmo sem conexão com a internet.
-   **Estado de Carregamento**: Uma tela de carregamento simulada na inicialização para melhorar a experiência do usuário.
-   **UI Moderna**: Construído inteiramente com Jetpack Compose e componentes de design Material3.

## Tecnologias

-   **Linguagem**: Kotlin
-   **Toolkit de UI**: Jetpack Compose (Material3)
-   **Arquitetura**: MVVM (Model-View-ViewModel)
-   **Navegação**: Navigation Compose
-   **Rede**: Retrofit & OkHttp
-   **Banco de Dados**: Room (SQLite)
-   **Concorrência**: Coroutines & Kotlin Flow
-   **Carregamento de Imagens**: Coil

## Boas Práticas e Arquitetura

O projeto adota diversas boas práticas de desenvolvimento Android moderno:

-   **MVVM (Model-View-ViewModel)**: Separação clara entre a lógica de negócios/dados e a interface do usuário.
-   **Repository Pattern**: Abstração da fonte de dados (local vs remota), fornecendo uma fonte única de verdade para o ViewModel.
-   **Offline-First**: Utilização do Room para persistir dados, garantindo que o app funcione mesmo offline.
-   **Coroutines & Flow**: Gerenciamento eficiente de operações assíncronas e fluxos de dados reativos.
-   **State Hoisting**: Gerenciamento de estado no Compose para tornar os componentes de UI reutilizáveis e testáveis.
-   **Clean Code**: Código estruturado e legível.

## Configuração

1.  Clone o repositório.
2.  Abra o projeto no Android Studio.
3.  Sincronize os arquivos Gradle.
4.  Execute o app em um emulador ou dispositivo físico.

## API

Este projeto utiliza a [The Movie Database (TMDB) API](https://www.themoviedb.org/documentation/api).

## Estrutura do Projeto

-   `data`: Contém fontes de dados (Room DB local, API Retrofit remota) e repositórios.
-   `ui`: Contém todos os componentes de UI, telas e definições de tema.
-   `navigation`: Contém o grafo de navegação e definições de destino.
-   `Entities`: Contém classes de dados/entidades.
