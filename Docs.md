# Sobre o projeto
Vim aqui pra falar um pouco sobre esse projeto.

Estou utilizando XcodeGen pra gerar o arquivo de projeto, então não versiono ele, no arquivo [project.yml](AppHotel/project.yml) temos as configs usadas.

Fiz também um Makefile pra facilitar um pouco os comandos
```sh
# Pra gerar o projeto
# OBS: Talvez seja preciso instalar o XcodeGen
make generate
```

Tinha pretensões de adicionar mais coisas nele como o gerenciamento de fastlane e etc.

# Layout
Utilizei ViewCode com UIKit.
Ainda um jovem padawan no SwifUI ahsuhaushas.

Fiz uma [extension](AppHotel/Sources/Extensions/UIView+Constraints.swift) pra facilitar a utilização das constraints. Ninguém merece a verbosidade daquilo né? uahsuhauhsahs

## Arquitetura
### Home
Pra tela de Home usei uma arquitetura que tenho me sentido mais confortável ultimamente, chamada **CleanSwift** (Adaptação de VIPER e Clean para swift)

Ela consiste de 3 partes básicas (VIP)
- View: Construção de view e somente gatilhos para os outputs
- Interactor: Lógica de negócio e orquestração de chamadas
- Presenter: Formatação de dados para exibição

Usei também um Router pra fazer a comunicação entre telas e um arquivo para separar toda a configuração da cena.

Como cada camada fica com suas responsabilidades bem definidas, os testes são para verificar que as informações são passadas corretamente de acordo com a logica de negócio.

Adicionei também um teste de Snapshot para a célula utilizada na Home. 📸

### RequestManager

Usando o módulo indicado pra *GraphQL* eu fiz um [NetworkManager](AppHotel/Sources/Network/NetworkManager.swift) mais genérico pra utilizar em diversas queries diferentes. 

Usando ele eu montei um [Repository](AppHotel/Sources/Network/SearchRepository.swift) mais específico para ter os parametros mais definidos.

### Details
Como essa tela não tinha muita lógica de negócio, optei por uma abordagem mais simples mesmo, tendo somente a view controller.

O layout foi feito usando uma separação por seções, dentro de uma table view. Assim consigo células com responsabilidade de exibição diferentes e também uma maneira mais modular de montar a tela.

## Testes
Como mencionei antes, fiz uns testes de fronteira entre as camadas e alguns de Snapshot.

Para mockar alguns objetos, fiz uma propertyWrapper `@Spy` pra ter acesso à alguns comportamentos sem ter que replicar tudo em todos os mocks.

## Bibliotecas adicionadas (SPM)
- [Kingfisher](https://github.com/onevcat/Kingfisher): pra gerenciar o download de imagens por url, adicionar placeholder e guardar cache
- [SnapshotTesting](https://github.com/pointfreeco/swift-snapshot-testing): Teste de snapshots
- [XcodeGen](https://github.com/yonaskolb/XcodeGen): Pra gerar o arquivo de projeto

## Conclusão
Acabei demorando um pouco pra pegar os esquemas ali do GraphQL, mas depois foi mais tranquilo.

Se tiverem quaisquer dúvidas ou questões, fiquem a vontade pra me chamar ;D