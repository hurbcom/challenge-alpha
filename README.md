
# Desafio Alpha

**Por:** [Rodrigo Malhães Bukowitz ](https://github.com/rodrigomalhaesbuko) < rodrigomalhaes@gmail.com >. 

## 1. Sumário

- [1. Sumário](#1-sumário)
- [2. O Projeto](#2-aplicação-hurbalpha)
- [3. O Design](#3-0-design)
     - [3.1. Feed](#31-feed)
- [4. Testes](#4-testes)
    - [4.1. Unitários](#41-unitários)
    - [4.2. UI](#42-ui)
- [5. Dependências](#5-dependências)
- [6. Vulnerabilidades](#6-vulnerabilidades)

## 2. Aplicação HurbAlpha
Arquitetura: **MVC**

Para uma melhor vizualização de todas as funcionalidades do aplicativo estou sempre realizando a busca de informações de :
`GET https://www.hurb.com/search/api?q=buzios&page=2`

Isso de deve ao fato de na pagina 1 de buzios não possuir a opção de pacotes nesse atual momento. 

O aplicativo HurbAlpha permite que o usuário contemple hotéis e pacotes de viagens na localidade de Búzios RJ. Por meio da API do Hurb. 
Por questões de escopo decidi usar a arquitetura **MVC**, a qual tenho já bastante familiaridade e possui rápida implementação. 

A aplicação possui uma tela de carregamento para os dados obtidos da API, uma TableView customizada com as ofertas oferecidas pelo Hurb e uma tela de falha caso a aplicação não consiga obter os dados. 


## 3. O Desing

Para o desing do aplicativo decidi utilizar padrões  próximos dos já encontradas nas demais aplicações do Hurb. 
Por esse motivo o aplicativo se mantem fiel a palheta de cores usual do Hurb, e a organização das celulas também se da dessa forma, sempre valorizando o preço e o nome do Hotel ou Pacote da oferta. 

### 3.1. Feed
Seguindo o padrão encontrado nas demais aplicações do Hurb o feed de ofertas e disposto da forma vertical, dando prioridade para os pacotes oferecidos.

Também existem duas telas adicionais uma para o carregamento do aplicativo que se da por meio de uma animação, e outra tela informando caso a aplicação não consiga obter os dados das ofertas. 

## 4. Testes 

Dado o escopo do aplicativo foram feitos 2 testes , um na parte de UI  e um teste unitário na parte da camada de rede e tratamento de dados. 

### 4.1. Unitários
O teste relacionado a camada de rede verifica a consistencia de da camada de rede além de verificar se os dados que chegam estão da forma desejada. 

### 4.2. UI
O teste de UI tenta cobrir todas as possiveis interações que o usuário teria ao utilizar o app, movendo o o feed para cima e para baixo tanto quanto queria. 


## 5. Dependências 

Para as dependências do projeto utilizei po CocoaPods, por sua simplicidade e pelo fato do aplicativo nnao demandar de tantas depedências 

### 5.1. [CocoaPods](https://cocoapods.org/)
#### 5.1.1. [Moya](https://github.com/Moya/Moya)
Moya torna muito mais pratico realizar requests em ambiente IOS em swift, ele é construido em cima do Alamofire que já é um framework altamente reconhecido e confial no ambiente IOS.
#### 5.1.2. [SnapKit](https://github.com/SnapKit/SnapKit)
SnapKit oferece uma forma muito mais rápida e legível para aplicar e usar constraits no seu codigo.  
#### 5.1.3. [Kingfisher](https://github.com/onevcat/Kingfisher)
Kingfisher torna a utilização de imagens obtidas da internet muito mais facil pois além de prático e fácil e eficiente pois armazena as imagens em cache, evitando downloads desnecessários  

## 6. Vulnerabilidades 

Uma das maiores vulnerabilidades do aplicativo é que este só pode ser utilizado quando possuir conexão com a internet.  




