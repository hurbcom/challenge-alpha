# Desafio Alpha

**Por:** [Theo Mendes](https://github.com/theomendes) < theodefranca@gmail.com >.

## Contúdo

* [O Projeto](#o-projeto)
* [Instalação](#instalação)
* [Design](#design)
* [Toque a mais](#toque-a-mais)
* [Testes](#testes)
* [Dependências](#dependências)
* [Vulnerabilidades](#vulnerabilidades)

## O Projeto
Arquitetura: **MVVM-C**

A escolha resta arquitetura deve-se ao fato de ser bastante testavel mas ainda ter um nivel de simplicidade que pode ser rápidamente estruturado. Implementei um Coordinator pois isso é uma das coisas que falta no MVVM uma camada de routing.

O projeto é separado em 3 Schemas: **Debug, AdHoc e AppStore** e 3 Build Configurations: **Development, Production, Stage**, tem uma bundle id, nome e icones diferentes para cada Build Configuration, para assim em um único aparelho poder se instaladas cada versão, o nome e icone são para mera diferenciação visual.

O projeto para evitar erro humano automaticamente detecta para qual schema esta buildando e pega as variaveis de ambientes de acordo, por exemplo você pode querer durante o desenvolvimento usar o localhost como url para consumir uma API, e uma url real para o app em produção. Para assim evitar que haja chance de acabar indo uma url de desenvolvimento para produção.

Usei tanto o CocoaPods, como Carthage. Fiz essa escolha pois como o Carthage compila os frameworks quando você pega eles, isso faz diminuir consideravelmente o tempo de compilação do projeto todo, assim aumentando a produtividade. Usei o CocoaPods somente pelo fato que nem tudo esta no Carthage, principalmente dois grandes frameworks muito usados: SwiftLint e SwiftGen.


## Instalação

### Prepare o ambiente
Para rodar este projeto você irá precisar do Ruby [Bundler](http://bundler.io), [Carthage](https://github.com/Carthage/Carthage) e [CocoaPods](https://cocoapods.org/)

Carthage pode ser instalado rodando o seguinte comando:

```bash
brew update
brew install carthage
```

O Bundle deve ter vindo junto com a instalação do Ruby. Você precisa do Ruby maior que 2.4. Se você não tem o bundle, pode ser instalado rodando o seguinte comando:

```bash
sudo gem install bundler
```

### Configuração do projeto
1. Na pasta Root do projeto rode
```bash
bundle install
```
2. Rode a instalação do CocoaPods
```bash
bundle exec pod install
```
3. Rode o Carthage
```bash
carthage bootstrap --platform ios --cache-builds --no-use-binaries
```
## Design

### Feed
Como o desafio não impunha um design especifico do feed, preparei algumas telas. Não quis seguir com o exemplo de tabulação dado no README do desafio pelo de ter ja de cara um problema de UX, o usuário para ir até a informação que precisa precisa escrolar muito, como por exemplo, se ele quiser procurar um hotel 3 estrelas, na tabulação de exemplo ele teria que percorrer um grande caminho e poderia até desistir da compra.

Analizando o app de vocês para iOS e o site, para mim ficou claro que vocês valorizam dar destaque para o local e o preço atrativo. Seguindo esta ideia resolvi aplicar um feed com sessões laterais, dentro das sessões laterais eu ordenei decrescente pela porcentagem de desconto, logo os hoteis que acabaram de ter desconto ficam por ultimo.

Percebi que a Hurb da bastante valor para os pacotes, levando em conta isso resolvi colocar os pacotes como a primeira coisa do feed e dando bastante destaque para foto.

Preparei também 2 telas extras, uma de carregando e outra de quando deu erro, por questão de escopo não preparei metodos para se recuperar do erro.

<p align="center">
    <img src="Design/FeedDesign.png" alt="Feed Design" />
</p>

## Toque a mais
* Além dos requisitos minimos, configurei o projeto de maneira segura para diversos ambientes, Dev, AdHoc e AppStore.
* Exibo além do preço atual, o desconto atual do local.
* Uso células diferenciadas para o Hotel e Pacotes
* Feed centrado na experiência do usuário
* Auto Layout, a célula de pacote se adapita a largura do celular, mantendo aspect ratio.
* Localizei o app tanto em Inglês quanto Português, incluindo o preço, como por exempo em **en_US** 3,000.00 em **pt_BR** 3.000,00. Mas tomei cuidado com o fato da API retornar a currency que o preço esta, então independente do caso vai sempre aparecer o simbulo como R$.

## Testes
Total de cobertura:  **86%**

### Unitários
Criei testes unitários para o ViewModel e o Network layer, usei um stub em formato json para mockar a resposta da API, para assim garantir a consistencia dos dados. o stup criei de uma resposta da API da hurb, mas deletei alguns objetos para garantir a diferença entre a API real para o stub

### UI
Pelo fato do app conter uma tela eu testei o scroll vertical, e swipe em duas sessões.

## Dependências

### [Bundler](http://bundler.io)
Uso ele para garantir consistencia entre as versões do cocoapods dos contribuidores do projeto, assim evitando problemas no Podfile.lock e consequentemente no workspace.

### [CocoaPods](https://cocoapods.org/)
#### [SwiftLint](https://github.com/realm/SwiftLint)
Creio que em grandes projetos tem que haver uma maneira de garantir que as regras de boas práticas sejam compridas, então não há escolha melhor que esta.
#### [SwiftGen](https://github.com/SwiftGen/SwiftGen)
Um dos grandes problemas, principalmente em assets, é garantir que eles sejam type-safe, escolhi ele para garantir que os Assets e arquivos de Localização fiquem todos type-safe.

### [Carthage](https://github.com/Carthage/Carthage)
#### [RxSwift](https://github.com/ReactiveX/RxSwift)
O forte do MVVM é a ligação entre a View e ViewModel, então bibliotecas reativas são muito bem vindas. Eu fiquei em duvida entre usar ele ou ReactiveSwift, ReactiveSwift esta presente a mais tempo no mundo Reativo do Swift e ele se aproxima mais a guidelines do Swift do que resto do mundo, por outro lado RxSwift se aproxima ao framework ReactiveX que esta disponivel em diversas plataformas logo ese tenta manter uma guideline padrão, creio que em um lugar como a Hurb com diversas linguagens de desenvolvimento seja mais atrativo manter um padrão entre elas.
#### [RxDataSources](https://github.com/RxSwiftCommunity/RxDataSources)
Uma falha no RxSwift é que ele não facilita a construção de feeds mais complexos que simples colections views ou table views, com isso para facilitar o trabalho resolvi usar ele.
#### [Moya](https://github.com/Moya/Moya)
Uma das grandes dificuldades em network layer é garantir que ele seja altamente testavel, escolhi o Moya por ele ser construido em cima do Alamofire, que ja esta a muito tempo presente no Swift, ser altamente testavel e type-safe.
#### [SnapKit](https://github.com/SnapKit/SnapKit)
Ficar escrevendo constraints é um trabalho que demanda um certo tempo, e de não ser muito bonito em escrever, SnapKit facilita bastante mas ainda se acemelha muito a lógica da constraint nativa.
#### [Kingfisher](https://github.com/SnapKit/Kingfisher)
Kingfisher oferece maneiras eficientes de fazer o download de imagens e guardar em cache, evitando que a pessoa tenha sempre que fazer download de imagem, assim autentando a responsividade do app e economizando gets na AWS ou outro serviço de hospedagem, o que significa menos $$ gasto.

## Vulnerabilidades

### Moya
Não é considerado uma vulnerabilidade, mas pelo fato de estar usando Swift 5.1 o Moya não atualizou certas dependências dele na versão estavel, tal como o ReactiveSwift que ainda esta na versão anterior assim não compilando. Com isso tive que usar a versão 14 Beta 4 do Moya.

### Imagens das celulas de collection view - HTTPS
No [HotelCollectionViewCell.swift](Alpha/Scenes/Feed/Collection%20View%20cells/HotelCollectionViewCell.swift) e [PackageCollectionViewCell.swift](Alpha/Scenes/Feed/Collection%20View%20cells/PackageCollectionViewCell.swift) tive que forçar o uso de HTTPS no link das imagens, ja que alguns dominios vinham da API usando protocolo HTTP, nos casos que analizei que não vinham como HTTPS, vinham com o dominio: omnibees.com, fiz uma checagem e vi que ele era compativel com HTTPS, logo por questões que a própria Apple impõe em aplicativos de produção, tive que forçar o HTTPs, mas como não conheço a API da Hurb não posso afirmar com certeza que todos os links vem da omnibees ou AWS, logo pode haver casos que a imagem não apareça no feed. 
