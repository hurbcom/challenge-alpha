
# <img src="https://avatars1.githubusercontent.com/u/7063040?v=4&s=200.jpg" alt="HU" width="24" /> Hurb

Aplicativo para dispositivo iOS (**Swift**) que consome o JSON exposto pela API de busca do Hurb e apresenta as informações em uma listagem. Há ainda a opção de buscar sugestões de lugares e visualizar os detalhes de um determinado produto.

## Screenshots
![Telas do Hurb](/screenshots/screenshots.png?raw=true)

## API
A API utilizada neste desafio foi do próprio Hurb, que fornece duas URLs: uma que entrega a listagem de resultados para um determinado local e outra que retorna uma lista de sugestões de lugares de acordo com o texto informado.

**URL:**

`GET https://www.hurb.com/search/api?q=rio+de+janeiro&sort=stars&page=1` 

Na URL acima, é passado como parâmetros o texto para busca, o item que será ordenado e o número da página. O documento JSON contem um nó chamado `results`, que contém os hotéis e pacotes do Rio de Janeiro.

----
`GET https://www.hurb.com/search/api/suggestion?q=rio+de+janeiro` 

Na URL acima, é passado como parâmetros o texto para busca. O documento JSON contem um nó chamado `suggestions`, que contém a lista de sugestões de lugares de acordo com o texto informado.

## Instalação do Projeto
 1. Fazer o clone do projeto:
`git clone https://github.com/alexpapanis/challenge-alpha.git`
 
 2. Pelo terminal, entrar na pasta /Hurb e rodar o comando:
`pod install`

3. Abrir arquivo `Hurb.xcworkspace`

4. Fazer o build e rodar o projeto

## Funcionalidades
- A interface foi inspirada no site do Hurb: as células no resultado da consulta e as cores (azul e rosa).
- Listagem inicial dos hotéis e pacotes com o termo "Rio de Janeiro".
	* Os hotéis estão ordenados em quantidade de estrelas (de 5 a 0) e, por último, os pacotes.
	* Algumas informações importantes foram adicionadas nas células, para melhor visualização do hotel, como nome, preço, cidade, estado, uma foto e três amenidades.
- Campo de busca de lugares. 
	* Ao digitar na busca, se possuir mais de 3 caracteres e o intervalo entre um caracter e outro for mais de 0,5 segundos, uma chamada à API será feita, simulando um autocomplete. 
- Tela de detalhes do hotel ou pacote
	* Há uma galeria de fotos. Ao deslizar o dedo sobre a galeria, é possível visualizar todas as fotos. Clicando sobre a foto, uma nova view é aberta em full screen. Ela pode ser dispensada clicando no X ou fazendo o swipe pra cima ou pra baixo na foto.
	* Há a lista de todas as amenidades.
	* Há o endereço completo
	* Há um botão para traçar rota pelo Google Maps, Apple Maps ou Waze.
	* Há um mapa para visualizar o endereço.

## Bibliotecas utilizadas
- **Google Maps**, para fazer o geocoding (conversão de endereço para coordenadas de latitude e longitude)  e visualizar o mapa na tela de detalhes.
- **Lottie**, para exibir as animações.
- **SwiftyJSON**, para auxiliar no tratamento das respostas JSON.
- **SDWebImage**, para fazer cache das imagens no dispositivo.
- **RxSwift/RxCocoa**, uma abordagem reactive para trazer novos elementos à lista de hotéis de acordo com a posição no scroll (implementação do scroll infinito).
- **ImageSlideshow**, uma biblioteca que ajuda a montar uma galeria de fotos.
- **FirebaseAnalytics**, para gravar eventos gerados no aplicativo e estudar o comportamento dos usuários.
 
## Testes
* Foi criado 2 testes unitários:
	* Buscar hotéis (utilizando a API)
	* Converter uma string de número para moeda.

* Foi criado 1 teste UI:
	 * Navegação para a tela de Busca e consultar o lugar "Búzios".

## Algo a mais
- Busca de sugestão de lugares
- Detalhe do hotel
- Galeria de fotos
- Mapa
- Eventos no Firebase
