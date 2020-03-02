# <img src="https://avatars1.githubusercontent.com/u/7063040?v=4&s=200.jpg" alt="HU" width="24" /> Projeto Hurb

Projeto desenvolvido em `Kotlin` para o atender ao desafio challenge alpha da Hurb.


## Requisitos Atendidos

-   Arauitetura utilizada `MVVM`

-   Chamada para api `GET https://www.hurb.com/search/api?q=buzios&page=1` usando `Retrofit2`.

-   Ler o resultado do JSON e colocar numa lista.
    
    -   Framework para o mapping do JSON `Moshi`

-   Exibição dos itens em forma de lista. Cada card vai representa um ítem do retorno de `results` e mostra seu nome, preço, uma foto e pequena descrição.

    -   Listagem com reresh e paginação.
    
    -   Agrupamento das células (usando um título para separá-las) pela quantidade de estrelas. Caso não exista estrelas, agrupe em _Pacotes_.
    
-   Utilização da lib `Picasso` para exibição e armazenamento em cache das imagens.
    
-   Tela de erro para o caso de não haver conexão.
    
-   Implementaçao de uma lista de favoritos 

    -   Ordenada em ordem decrescente pela quantidade de estrelas.
    
    -   Utilização do `Room` para amazenamento dos itens
    
-   Implementação de uma tela com detalhes do Item.

    -   Contem uma imagem, Tiulo, descrição, preço e quantidade de estelas

-   Implementação de busca usando `SearchView`

-   Layouts com Constraint Layout

-   Log dos dados recebidos `Okhttp3`

-   Framework de injeção de dependência `koin

-   Architecture Components utilizados `Navigation, LiveData, Room, ViewModel`


<p align="center">
  <img src="ca.jpg" alt="Challange accepted" />
</p>
