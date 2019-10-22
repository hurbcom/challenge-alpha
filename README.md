# Desafio Alpha

**Por: Theo Mendes.**

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
## Dependências
* [Bundler](http://bundler.io)
* [CocoaPods](https://cocoapods.org/)
    * [SwiftLint](https://github.com/realm/SwiftLint)
    * [SwiftGen](https://github.com/SwiftGen/SwiftGen)

* [Carthage](https://github.com/Carthage/Carthage)
    * [RxSwift](https://github.com/ReactiveX/RxSwift)
    * [RxDataSources](https://github.com/RxSwiftCommunity/RxDataSources)
    * [Moya](https://github.com/Moya/Moya)
    * [SnapKit](https://github.com/SnapKit/SnapKit)
    * [Kingfisher](https://github.com/SnapKit/Kingfisher)

## Design
### Feed
Como o desafio não impunha um design especifico do feed, preparei algumas telas. Não quis seguir com o exemplo de tabulação dado no README do desafio pelo de ter ja de cara um problema de UX, o usuário para ir até a informação que precisa precisa escrolar muito, como por exemplo, se ele quiser procurar um hotel 3 estrelas, na tabulação de exemplo ele teria que percorrer um grande caminho e poderia até desistir da compra.

Analizando o app de vocês para iOS e o site, para mim ficou claro que vocês valorizam dar destaque para o local e o preço atrativo. Seguindo esta ideia resolvi aplicar um feed com sessões laterais, dentro das sessões laterais eu ordenei decrescente pela porcentagem de desconto, logo os hoteis que acabaram de ter desconto ficam por ultimo.

Percebi que a Hurb da bastante valor para os pacotes, levando em conta isso resolvi colocar os pacotes como a primeira coisa do feed e dando bastante destaque para foto.

Preparei também 2 telas extras, uma de carregando e outra de quando deu erro, por questão de escopo não preparei metodos para se recuperar do erro.

<p align="center">
    <img src="Design/FeedDesign.png" alt="Challange accepted" />
</p>

## Vulnerabilidades
### Moya
Não é considerado uma vulnerabilidade, mas pelo fato de estar usando Swift 5.1 o Moya não atualizou certas dependências dele na versão estavel, tal como o ReactiveSwift que ainda esta na versão anterior assim não compilando. Com isso tive que usar a versão 14 Beta 4 do Moya.

### Imagens das celulas de collection view - HTTPS
No [HotelCollectionViewCell.swift](Alpha/Scenes/Feed/Collection%20View%20cells/HotelCollectionViewCell.swift) e [PackageCollectionViewCell.swift](Alpha/Scenes/Feed/Collection%20View%20cells/PackageCollectionViewCell.swift) tive que forçar o uso de HTTPS no link das imagens, ja que alguns dominios vinham da API usando protocolo HTTP, nos casos que analizei que não vinham como HTTPS, vinham com o dominio: omnibees.com, fiz uma checagem e vi que ele era compativel com HTTPS, logo por questões que a própria Apple impõe em aplicativos de produção, tive que forçar o HTTPs, mas como não conheço a API da Hurb não posso afirmar com certeza que todos os links vem da omnibees ou AWS, logo pode haver casos que a imagem não apareça no feed. 
