# Challenge-alpha
Desafio mobile

*Por:** [Luiz Fernando Duate](https://github.com/LuizFCDuarte) < luizfcduarte@outlook.com>.

## 1. Conteúdo

- [1. Conteúdo](#1-conteúdo)
- [2. O Projeto](#2-o-projeto)
- [3. Design](#3-design)
- [4. Testes](#4-testes)
    - [4.1. Unitários](#41-unitários)
    - [4.2. UI](#42-ui)
- [5. Dependências](#5-dependências)
    - [5.1. CocoaPods](#51-cocoapods)
        - [5.1.1. SwiftLint](#511-swiftlint)
        - [5.1.2. Moya](#512-moya)
        - [5.1.3. SnapKit](#513-snapkit)
        - [5.1.4. Kingfisher](#514-kingfisher)
        - [5.1.5. UPCarouselFlowLayout](#514-upcarouselflowlayout)
- [6. Vulnerabilidades](#6-vulnerabilidades)
    - [6.1. Protocolo de rede usado por algumas imagens](#61-protocolo-de-rede-usado-por-algumas-imagens)

## 2. O Projeto 
Arquitetura: **MVC-C**  

Escolhi essa arquitetura uma vez que essa atenderia aos requisitos necessários do projeto e não intruduz uma complexidade maior a este. Além disso, implementei um Coordinator, para tirar das ViewControllers a lógica de fluxo do app, exugando o código delas.
Para uma que todos os detalhes implementados fossem expostos, a consulta a API padrão é feita com a URL :
`GET https://www.hurb.com/search/api?q=buzios&page=2`
O app conta com uma tela de carregamento e um feed que mostra os pacotes e os hoteis categorizados por número de estrelas.

## 3. Design

Para as escolhas do design do app decidi me inspirar no app iOS da Hurb e também no site da empresa. Além disso, olhei para soluções similares para buscar inspirações de design. 
Desse modo, decidi estruturar o feed como uma tableView. A primeira secção dessa exibe os pacotes se existirem e as demais exibem os hotéis agrupados pelo número de estrelas. A exibição dos hotéis e pacotes é feita com uma collectionView com scroll horizontal.

<p align="center">
    <img src="Assets/appDemo.png" alt="appDemo" />
</p>

## 4. Testes

### 4.1. Unitários

Para garantir que as principais funcionalidades do app fossem supridas e o app funcionasse conforme o esperado, preparei testes para o NetworkAdapter e o Data Manager. Desse modo, pude garantir a consistência das informações recebidas da API e como elas são tratadas pelo app. 

### 4.2. UI

O teste de UI, consiste em avaliar o scroll vertical do feed e o scroll horizontal das células deste

## 5. Testes

### 5.1. CocoaPods

#### 5.1.1. [SwiftLint](https://github.com/realm/SwiftLint)
Para garantir uma padronização do código, adicionei o SwiftLint, o qual em projetos em larga escala auxilia na legibilidade e manutenção do código.

#### 5.1.2. [Moya](https://github.com/Moya/Moya)
Optei por usar o Moya, ao invés de lidar com o request manualmente, visto que apesar de adicionar uma dependência ao projeto, ela permite uma maior escalabilidade  organização dele.

#### 5.1.3. [SnapKit](https://github.com/SnapKit/SnapKit)
Com o SnapKit é mais simples a criação de constraints via código. Além disso, a lógia utilizada por ele se assemelha a nativa do UIKit, o que garante mais agilidade na confecção de constraints.

#### 5.1.4. [Kingfisher](https://github.com/SnapKit/Kingfisher)
Essa ferramenta oferece maneiras eficientes para fazer o dowload de imagens, auxiliando na responsividade e na diminuição do número de requests para o serviço de hospedagem

#### 5.1.5. [UPCarouselFlowLayout](https://github.com/ink-spot/UPCarouselFlowLayout)
Essa ferramenta permite que a collectionView tenha o layout em estilo Carousel, melhorando a experiência do usuário

## 6. Vulnerabilidades 

### 6.1. Protocolo de rede usado por algumas imagens
Algumas urls para imagens não conformavam com o protocolo https, dessa forma uma vez que a Apple exige de apps em produção que ultilizem urls que conformem com esse protocolo, resolvi substituir o protocolo dessas imagens por um https, o que pode garantir vulnarabilidades à aplicação. 


