# APP para o desafio HURB

Hotél Búzios é um app de listagem de hospedagens e pacotes de viagem para a cidade de Búzios/RJ.

### Especificações técnicas do app

**Arquitetura:** Foi utilizado a arquitetura MVVM-C ou Model-View-ViewModel com Coordinators, mesmos endo um app simples com uma tela, essa arquitetura foi escolhida pensando que poderia ser acrescentado novas features, e quando fosse feito, seria de uma maneira mais fácil, pois o app está respeitando bastante principios de SOLID, e e e bem modularizado.

**Linguagem:** Foi utlizado swift 5.

**Suporte de versão:** O app suporta a partir do iOS 10.

**Testes:** O app contém testes unitários e testes de UI, utilizando os frameworks nativos para isso, totalizando 85,7% de cobertura do app.

**Gerenciador de dependência** Foi utlizado Cocoapods.

**Pods:** Foi utlizado apenas dois, Alamofire e Kingfisher, o restante foi feito de forma nativa, deixando o app mais leve e rápido para buildar.

**UX:** O app foi pensado para ser bem simples de ser usado, até por ter apenas uma tela, e pelo curto tempo para criação do mesmo. Tudo se resume a tela principal, onde tem a barra de nevegação com o titulo do app, abaixo um header com o titulo da seção que está divida por quantidade de estrelas indo do maior(5 estrelas) ate 0 mais baixo(3 estrelas), e por ultimo a seção de pacotes. 

**Instruções para testar o app:** Clone o projeto, pelo fato de ele ter apenas dois pods, não foi incruido um gitignore, então ele já irá junto com os pods, não precisando dos comando de pod install. Abra o workspace, CMD+B, CMD+R e se quiser rodar os testes CMD+U.


