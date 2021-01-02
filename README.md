# <img src="https://avatars1.githubusercontent.com/u/7063040?v=4&s=200.jpg" alt="HU" width="24" /> Desafio Alpha

Esta aplicação tem por objetivo consumir os seguintes serviços da Hurb:

`GET https://www.hurb.com/search/api?q=buzios&page=1` (Obrigatória)

`GET https://www.hurb.com/search/api/suggestion?q=buzios` (Opcional)

A aplicação utiliza Swift Package Manager para consumir as bibliotecas e portanto, mas abrir o arquivo **Hurb.xcodeproj** para que a mesma funcione.
- As seguintes funcionalidades foram cobertas nesta aplicação:
  - Listagem de hoteis seguindo o desafio.
  - Listagem de lugares a serem utilizados na pesquisa de hoteis




## Frameworks

-   A aplicação utiliza os seguintes frameworks:
    -   **SwiftUI**: Framework da própria Apple para criação das interfaces de forma rápida e simples. Optei por utilizar o SwiftUI ao invés do UIKit por acreditar que o mesmo é mais rápido para desenvolvimento rápido que o desafio pedia.
    -   **Combine**: Framework reativo desenvolvido pela própria Apple, optei por utilizar o mesmo por facilitar e simplificar nas chamadas a outras camadas da aplicação.
    -   **Alamofire**: Framework utilizado para chamadas a serviços que proveem ferramentas que facilitam nas chamadas aos serviços.
    -   **Kingfisher**: Framework utilizado para carregar e exibir as imagens, o framework contém diversas ferramentas que facilitam nessa chamada, além de já estar 100% integrado ao SwiftUI.
    -   **SwiftUI-Introspect**: Framework que expoe as propriedades do UIKit ainda não disponíveis no SwiftUI para ele.
    
