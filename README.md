iTravel - Aplicativo desenvolvido para o teste challenge-alpha da Hurb.

 O aplicativo conciste de duas telas essencialmente, a primeira tela com a listagem dos pacotes de viagem e a segunda tela contendo a descrição de cada pacote.\n

 A arquitetura utilizada foi o MVVM-C\n

 Foi utilizado: \n
     RXSwit principalmente para os dados adiquiridos da API
     Alamofire em conjunto com a biblioteca ImageSlideShow para os slides de imagems nas celulas da tableView principal\n
     Toast-Swift para apresentar toast ao usuario caso algum erro acontecesse\n
     XCTest para implementação de testes unitarios\n
     Apollo para a conexão com a API\n
     Cocoapods para gerenciamento de dependências\n

 PARA RODAR O APP: \n
     Como foi utilizado o cocoapods como gerenciador de dependencias, para rodar o app será necessario:\n
     1 - Abrir o terminal\n
     2 - digitar " cd <PATH_TO_XCODE_PROJECT_FILE> "\n
     3 - digitar pod install\n
     4 - Abrir o arquivo .workspace e buildar o app\n
 Godspeed! ;)
