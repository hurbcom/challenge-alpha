# Marlon D. Rocha #

## Aplicativo Star Wars

### BuildInfo
 * Gradle: 8.2
 * Kotlin: 1.9.0
 * Java: 1.8
 * compileSdk: 33
 * targetSdk: "31"
 * minSdk: "23"

### Recursos utilizados
 * Jetpack Compose
 * Material3
 * MVVM
 * Navigation
 * Room
 * Paging
 * RemoteMediator
 * Koin (injeção de depencência)
 * Retrofit/OkHttp
 * Gson
 * Compose Animation
 * Theme Light/Dark

### Features
 * SplashScreen - Tela com animação na logo e com componente customizado de fundo que simula o brilho das estrelas no espaço.
 * HomeScreen - Tela com uma NavigationBar, contendo os ícones de navegação para das telas "Categories", "Favorites" e "Last seen".
 * CategoriesScreen - Tela que lista as categorias, buscando da api citada na documentação.
    * OBS: Foram construídas as telas de listagem de filmes e personagens. Neste caso, ao tocar em um item da tela que não seja uma
    * destas categorias, um Toast será exibido com uma mensagem informando que a categoria selecionada ainda não foi implementada.
 * FilmsScreen - Tela com lista de filmes, com paginação utilizando Room, Paging e RemoteMediator.
   * RemoteMediator - Classe responsável por gerenciar e paginar as requisições, primeiramente buscando dados da API e salvando no Room,
   * mantendo para esta tela o armazenamento interno como fonte de dados para a ViewModel.
 * CharactersScreen - Segue mesma linha de desenvolvimento da tela FilmsScreen.
 * FavoritesScreen e LastSeenScreen são alimentadas com informações do Room, de acordo com items visualizados/favoritados em suas respectivas telas de detalhe.
 * FilmDetailScreen - Tela de detalhe do filme, que busca os dados da lista de filmes salva no Room.
 * CharacterDetailScreen - Segue mesma linha de desenvolvimento da tela FilmDetailScreen.

### Débitos técnicos
 * Tive um problema ao tentar escrever os testes para o RemoteMediator, onde fiquei muito tempo tentando solucionar o problema,
 * que acabou me tirando tempo para focar em escrever mais testes (unitários e instrumentados).
 * Na classe RemoteMediatorTest há 3 testes ignorados, que foram os que escrevi mas está caindo numa exceção no Room que não encontrei a resposta à tempo.

### O que faria se tivesse mais tempo
 * Criaria mais cenários de teste.
 * Procuraria criar uma animação na SplashScreen, simulando a conhecida abertura dos filmes da franquia, com uma coluna de texto subindo e desaparecendo no céu escuro

### À disposição para dúdidas =D
