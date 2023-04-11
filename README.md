iTravel - Aplicativo desenvolvido para o teste challenge-alpha da Hurb.

O aplicativo conciste de duas telas essencialmente, a primeira tela com a listagem dos pacotes de viagem e a segunda tela contendo a descrição de cada pacote.

A arquitetura utilizada foi o MVVM-C

Foi utilizado: 
    RXSwit principalmente para os dados adiquiridos da API
    Alamofire em conjunto com a biblioteca ImageSlideShow para os slides de imagems nas celulas da tableView principal
    Toast-Swift para apresentar toast ao usuario caso algum erro acontecesse
    XCTest para implementação de testes unitarios
    Apollo para a conexão com a API
    Cocoapods para gerenciamento de dependências
   
PARA RODAR O APP: 
    Como foi utilizado o cocoapods como gerenciador de dependencias, para rodar o app será necessario:
    1 - Abrir o terminal
    2 - digitar " cd <PATH_TO_XCODE_PROJECT_FILE> "
    3 - digitar pod install
    4 - Abrir o arquivo .workspace e buildar o app
Godspeed! ;)
