
## Hotels App
Aplicativo que consume a API do Hotel Urbano e exibe essas informações de forma amigável ao usuário:
![Página principal](Assets/Home.png)

### Como usar?
Nosso aplicativo utiliza o CocoaPods como gerenciador de pacotes, portanto para instalar as bibliotecas utilizadas é necessário executar o seguinte comando no diretório do projeto: 

` pod install `

O comando deve gerar um arquivo chamado `Hotels.xcworkspace` que, a partir daqui, deve ser aberto no lugar do `Hotels.xcproject`.

### Testes
Foram criados testes unitários e um teste simples de interface, que abrange o uso do aplicativo.

### Integração contínua
No projeto, foi utilizado o Travis CI, um servidor de Integração Contínua que possui integração com GitHub. No servidor, configuramos 3 jobs, que são executados a cada push no projeto:
* O Travis deve realizar o build do projeto.
* O Travis deve executar os testes unitários.
* O Travis deve executar o teste de interface.
