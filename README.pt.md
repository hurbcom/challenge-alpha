# <img src="https://avatars1.githubusercontent.com/u/7063040?v=4&s=200.jpg" alt="HU" width="24" /> Desafio Alpha

[[English](README.md) | [Português](README.pt.md)]

Um app android que consome a partir de uma chamada a API uma lista de hoteis.

#### [>>>>> Ver o wiki para mais detalhes do desafio. <<<<<](https://github.com/hurbcom/challenge-alpha/wiki)

## Tech Stack

-   Dagger Hilt para injeção de dependência;
-   Bibliotecas do Android Jetpack:
    - LiveData
    - ViewModel
    - ViewBinding
    - Navigation Component
    - Material Design Components
    - Test
-   Kotlin Coroutines
-   Coil para carregar as imagens
-   Retrofit para chamada a API REST
-   Detekt & KtLint para análise estática
-   Mockk para mockar testes.

## Funcionalidades extras:

- O usuário ao clicar em um dos hotéis da lista, é direcionado para uma tela de detalhes.
- Na tela de detalhes, há um carrossel de images. O usuário ao clicar na imagem que está sendo exibida, irá para uma tela em que a imagem é exibida por completo.

## Serviço de API do desafio
- REST API