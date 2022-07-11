# Hurb Challenge

Nesse desafio do processo seletivo da hurb o objetivo foi construir um aplicativo que consumisse uma API que fornece dados referentes à saga StarWars. Além de listar categorias e detalhar os elementos em uma página de detalhes, um dos objetivos foi a criação de um fluxo de favoritos, que tem como base o armaezenamento local do telefone do usuário

# Pontos importantes

## Menu inicial

O menu inicial foi construído a partir de um json local, pois a rota inicial da API só fornece uma url para cada categoria, e mesmo acessando essas url's não temos as informações de nome da categoria e imagem das quais preciamos para fazer a listagem inicial.

## Listagem de items por categoria

Ao clicar em uma categoria pode-se ntoar que foi implementada uma caixa de texto para que o usuário possa digitar e buscar pelo elemento em questão, mas caso não queira digitar ele também pode scrollar infinitamente pela página em busca do elemento desejado. 

## Detalhe do item

Na página de detalhe do item são exibidas cinco informações referentes ao mesmo, e, na toolbar, há a opção de adicionar esse elemento como favorito

## Favoritos

Na página de favoritos são exibidos os items que o usuário favoritou, constando também a opção de remover esse item da lista através do ícone da lixeira.

## Bibliotecas
Para o armazenamento local foi escolhido o RoomDB, uma opção muito mais sólida e atual que o antigo SQLite

Para navegação optei por utilizar o jetpack navigation, que se baseia em haver apenas uma activity no projeto e construir as telas em cima de fragments

O consumo da API ficou a cargo do Retrofit, assim como a injeção de dependência ficou a cargo do Hilt

Todo o trabalho assíncrono foi realizado atraveś de coroutines

# Comentários pertinentes
Devido à limitação de tempo o design do app ficou minimalista por assim dizer, mas dei foco na otimização das funcionalidades de listagem, armazenamento local (favoritos) e scroll infinito, assim como também dei foco na arquitetura do projeto e na separação das camadas do mesmo, seguindo os padrões de arquitetura limpa.