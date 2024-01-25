# Challenge Alpha

O Challenge Alpha é um aplicativo Android desenvolvido com o objetivo de consumir a API Rest [SWAPI](https://swapi.dev/api/), que contém informações relacionadas à saga Star Wars.

O aplicativo oferece duas opções principais: "Vehicles" e "Starships". Ao selecionar uma dessas opções, o usuário pode visualizar uma lista dos modelos correspondentes, cada um exibindo poucos detalhes. Ao escolher um modelo específico, é possível acessar informações mais detalhadas sobre o mesmo.

Além disso, o aplicativo oferece uma funcionalidade de filtragem na tela de listagem, permitindo aos usuários encontrar facilmente um modelo desejado pelo nome. 😊

## Abordagem durante o desenvolvimento

1. **Arquitetura:** O Challenge Alpha adota a arquitetura single activity com MVVM combinada com os princípios da Clean Architecture. Essa abordagem proporciona escalabilidade e testabilidade ao projeto, promovendo uma estrutura modular e organizada. O uso de UseCases reforça a separação de preocupações, simplificando operações específicas do domínio.
2. **UI com Jetpack Compose + XML no mesmo projeto:** Como a aplicação é pequena e criada do zero seria bem tranquilo utilizar somente uma das opções, mas decidi me desafiar e utilizar a interação entre as duas tecnologias para demonstrar meus conhecimentos. No projeto temos:
   - Navigation Component para navegarmos entre os Fragmentos do app.
   - Telas feitas com XML e utilizando dentro delas componentes feitos com Jetpack Compose
   - Telas feitas com Jetpack Compose dentro de Fragments ( nesse caso como estou utilizando o navigation para XML, decidi deixar dessa forma e desenhar somente a tela, mas podemos converter as demais telas para Compose e utilizar o Navigation Compose eliminando a necessidade de Fragments )
     

![preview](https://github.com/wesleyerick/challenge-alpha/blob/master/readme_files/layout.png)


## Demonstração de telas

  - **Icone**
  
![preview](https://github.com/wesleyerick/challenge-alpha/blob/master/readme_files/icon.png)

- **Home**
  
![preview](https://github.com/wesleyerick/challenge-alpha/blob/master/readme_files/home.png)

- **Lista "Vehicles"**
  
![preview](https://github.com/wesleyerick/challenge-alpha/blob/master/readme_files/vehicles_without_filter.png)

- **Detalhes "Vehicle"**
  
![preview](https://github.com/wesleyerick/challenge-alpha/blob/master/readme_files/vehicle_details.png)

- **Lista "Starships" usando o filtro**
  
![preview](https://github.com/wesleyerick/challenge-alpha/blob/master/readme_files/starship_with_filter.png)

- **Detalhes "Starship"**
  
![preview](https://github.com/wesleyerick/challenge-alpha/blob/master/readme_files/starship_details.png)



## Tecnologias Utilizadas

O **Challenge Alpha** foi desenvolvido utilizando as seguintes tecnologias e bibliotecas:

 
- **Jetpack Compose:** Utilizado para a criação da interface do usuário de forma declarativa.
      - Versão: `1.0.0`
  
  - **Compose ConstraintLayout:** Extensão do Jetpack Compose para o uso de layouts baseados em constraints.
        - Versão: `1.0.1`
  
  - **Coil:** Biblioteca para carregamento de imagens eficiente em Compose.
        - Versão: `1.3.2`

- **Koin:** Framework de injeção de dependência.
        - Versão: `3.2.2`

  - **Koin Test:** Extensão do Koin para simplificar testes de componentes injetados.
        - Versão: `3.2.2`

- **Testes:**
  - **JUnit:** Framework de teste unitário para Java.
        - Versão: `4.13.2`
  - **JUnit Test:** Extensão do JUnit para testes no Android.
        - Versão: `1.1.5`
  - **MockK:** Biblioteca de mock para Kotlin.
        - Versão: `1.12.4`
  - **Coroutines Test:** Utilizado para testes de código assíncrono.
        - Versão: `1.6.1`

- **Outras Bibliotecas Relevantes:**
  - **ConstraintLayout:** Biblioteca de layout para o Android.
        - Versão: `2.1.4`
  - **Retrofit:** Biblioteca para chamadas de API RESTful.
        - Versão: `2.9.0`
  - **Glide:** Biblioteca para carregamento e exibição eficiente de imagens.
        - Versão: `4.15.1`
  - **Navigation Component (Fragment):** Componente de navegação para gerenciar a navegação no aplicativo.
        - Versão: `2.5.3`
  - **Lifecycle LiveData e ViewModel:** Componentes do Android Architecture Components para gerenciamento do ciclo de vida.
        - Versões: `2.1.0`

## Agradecimentos

Obrigado por avaliar o projeto, se tiver algum elogio, sugestão ou melhoria dos conceitos apresentados, ficarei imensamente feliz com sua ajuda para evoluir cara vez mais!

Muito brigado! 🚀
