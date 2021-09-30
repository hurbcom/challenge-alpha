# Hurb Challenge

É o projeto / desafio do processo seletivo. O app simula um request na API ao ler um arquivo JSON presente (mais informações em "Comentários Pertinentes"), localmente, no aplicativo. O app mostra uma listagem de hotéis com informações sobre localização, facilidades e preço. 
É possível visualizar a listagem, realizar buscas, aplicar filtros, adicionar um hotel aos favoritos e compartilhar um hotel.

# Decisões

## Antes de iniciar o projeto

**API Mínima exigida:** 23. Foi utilizada a mesma API mínima exigida pelo aplicativo Hurb (https://play.google.com/store/apps/details?id=br.com.hotelurbano&hl=pt_BR&gl=US) em sua versão mais recente (22/09/21).

**BottomNavigationView:** de acordo com a documentação do Material Design, a bottom navigation deve ter entre 3 e 5 itens (funcionalidades). A BottomNavigation permite uma ergonomia maior, mesmo em telas maiores. Também permite a adição de novas features (como um menu "perfil") sem maiores alterações no layout da Activity Principal. 
Por esse motivo, mesmo tendo apenas duas features, foi feita a escolha pela BottomNavigation.

## Durante o desenvolvimento do projeto

**Layout:** O layout foi pensado para ser simples, mas sem deixar de trazer as principais informações, para que não fosse necessário entrar na tela de detalhe.
O layout exibe o nome do hotel, localização, uma foto principal, uma pequena descrição do hotel e 10 facilidades que foram selecionadas como de maior importância. São elas (em ordem que aparece no layout):
- Academia
- Acessibilidade para cadeira de rodas
- TV no quarto
- Banheiro
- Bar
- Estacionamento
- Wifi
- Piscina
- Recepção 24h 
- Restaurante

# Features

## Listagem
Exibe os hotéis retornados pela API em uma listagem e exibe informações selecionadas (Nome, Localização, Foto, Pequena descrição e Facilidades)

## Busca
No topo da listagem há uma caixa de buscas onde é possível inserir o nome de uma cidade, estado ou país. A listagem irá retornar hotéis presentes em cidades, estados ou países cujo nome possui o termo inserido.

## Favoritos 
Ao entrar na tela de detalhe de um hotel, é possível clicar no ícone do coração no canto superior direito. O hotel é salvo em um banco de dados local (Android ROOM) e exibido em uma listagem diferenciada.


# Comentários pertinentes
A simulação de requests é feita utilizando o Retrofit (REST). Conforme explicado na Closed Issue ( https://github.com/hurbcom/challenge-alpha/issues/51 ), não há implementação da API Rest em produção.
Decidi utilizar o REST por ser a tecnologia que conheço, o que me ajudaria em relação ao tempo de desenvolvimento. 
Como todo o código, com exceção do Remote Data Source, é agnóstico em relação à comunicação com API e ao objeto de resposta, uma implementação do GraphQL não iria exigir grandes modificações no projeto.

