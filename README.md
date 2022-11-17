# <img src="https://avatars1.githubusercontent.com/u/7063040?v=4&s=200.jpg" alt="HU" width="24" /> Alpha Challenge

## Como rodar o projeto:

- O projeto se encontra na pasta "projects > Challenge_iOS".
- Abrir com o XCode.

## Anotações sobre a construção do projeto:

- Criei o projeto no Padrão de Projeto MVVM.
- A única lib usada foi o 'Kingfisher' para cache das imagens.
- Tabbar com 3 cenas [Pesquisar | Hotel | Pacotes], cada uma consumindo sua rota especifica + view de sugestões consumindo a sua rota especifica. Usando o GraphQL.
- Ao pesquisar um Pacote e clicar na célula irá acessar a tela de detalhes do Pacote.
- As células de Hotel e Pacote seguem o layout das células originas do App da Hurb.
- A tela de Detalhes do Pacote segue o layout original do App da Hurb.
- O desafio contempla testes Unitários, incluindo de Integração para as camadas de Service. Totalizando pouco mais de 90% de cobertura.

### Nota:
- Tela de Detalhes do Hotel: Por que questão de tempo não consegui desenvolver a tela de detalhes do Hotel, apenas a tela de Detalhes do Pacote, seguindo o Layout original do App da Hurb.