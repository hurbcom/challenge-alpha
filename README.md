##Teste

Projeto foi desenvolvido com a targetSdk 33 e minSdk 23 usado o Android Studio Electric Eel | 2022.1.1 Patch 2
O projeto possui uma tela de home que mostra as categorias e possui um um bottom navigation com as telas de últimos vistos e favoritos. Possui também telas de detalhes dos personagesn e dos filmes.


## Features e libs

Listagem das categorias e filmes.
Listagem dos personagens com paging 3
Offline first.
Visualização de detalhes dos personagens e dos filmes.
Os estados são mantidos quando a tela é rotacionada.
É possível visualizar os 9 útimos itens vistos.
É possível visualizar todos os personagens e filmes que foram favoritados.
É possivel pesquisar na listagem de filmes por qualquer estring que contenha no título.
É possível pesquisar na listagem de personagens por qualquer string que contenha no nome.
A navegação do projeto é feita atráves da lib de navigation e com o bottom navigation, existe animação nos directions do navigation.
Foi usado hilt na injeção de dependência 
Foi criado uma task para gerar um git hooks nos pre commits para analisar o padrão do projeto. É usada as libs ktlint e detek junto com .editorconfig para manter os padões de organização do código.
O gerenciamento das versões é feito através do version catalog que é a maneira mais atual que a comunidade está recomendando no momento e também é usado o gradle dsl kotlin.
No banco de dados a lib usada é o room.
Foi usado o timber pra printar os logs.
A splash screen é feita com a nova lib de splashscreen.
Foi usado flow em todo o projeto.
As libs usadas pra request foram do retrofit, moshi e okhttp, foi usado tambem o okhttp interceptor para mostrar os dados de resposta na ambiente de debug.
Coil foia lib usada para mostrar e cachear as imagens.
Foram feitos testes com junit, mockk.
Foi usada a lib leakcanary para acompanhar possíveis vazamentos de memória.
O app foi desenvolvido com xml.
O app pode ser facilmente modularizado pois mantém as estratégias da modularização com as dependências entre os módulos.
