# <img src="https://avatars1.githubusercontent.com/u/7063040?v=4&s=200.jpg" alt="HU" width="24" /> Desafio Alpha -- Hotel Urbano

**Objetivo**: Criar um aplicativo para dispositivo móvel (**Objective-C**, **Swift**, **Kotlin**, **React Native**) que consuma o JSON exposto pela API de hotéis e o exiba usando uma _table view_. Você pode usar bibliotecas de terceiros para criar sua aplicação.

**Escolha**: **App Android**

## API Utilizada
**URL:**
`GET https://search.hotelurbano.com/api?q=Rio%20de%20Janeiro`

## Requisitos Cumpridos
- Forkar esse desafio e criar o seu projeto (ou workspace) usando a sua versão desse repositório, tão logo acabe o desafio, submeta um *pull request*.
- Faça chamadas assíncronas para a API e emita um log dos dados recebidos.
- Ler o resultado do JSON e colocar numa lista chamada `Hotels`.
- Exibir os itens de `Hotels` numa UITableView ou UICollectionView (como no iOS, por exemplo). Cada célula vai representar um ítem de `Hotels`e deve mostrar seu nome, preço, cidade, estado, uma foto  e três amenidades.
- Agrupar as células (usando um título para separá-las) pela quantidade de estrelas. Caso não exista estrelas, agrupe em *Pacotes*.
  - Deve estar ordena descrentemente pela quantidade de estrelas.
- Ter testes para o código criado

## Principais Features

- Arquitetura MVVM
- Uso dos Android Architecture Components (LiveData, ViewModel e Room)
- Aquitetura Offline First - O App primeiro busca informações persistidas para depois consultar a API. Após um request bem-sucedido, é possível utilizar o App mesmo sem conexão com a internet
- Sincronização com o banco remoto de tempos em tempos (para evitar chamadas repetitivas)
- Injeção de Dependências com o Dagger2
- Camada de abstração do Networking conectando Retrofit, RxJava2, Room e LiveData
- Constraint Layout
- RecyclerView dividida por secções
- CardView expansível
- Animações (simples)
- Picasso para o carregamento de imagens
- Swipe to refresh
- Organização dos scripts .gradle
