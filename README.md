# <img src="https://avatars1.githubusercontent.com/u/7063040?v=4&s=200.jpg" alt="HU" width="24" /> Desafio Alpha

Crie um aplicativo para dispositivo móvel (**Swift**, **Kotlin**, **React Native**, **Flutter**) que consuma o JSON exposto pela API de busca e apresente as informações em uma listagem. Você é livre para utiliar bibliotecas de terceiros. 

**Atenção**: a apresentação em forma de listagem é apenas o requisito mínimo do desafio. Sinta-se livre para construir a sua solução. Exemplo: implementação de uma barra de busca com sugestões, detalhes de um certo produto, etc. 

Queremos ver o **máximo da sua capacidade técnica**! Quem decide o limite é você.


## API
A URL a seguir entrega a listagem de resultados para a cidade de Búzios como um documento JSON.

O documento contem um nó chamado `results` o qual tem os hotéis do Rio de Janeiro que você precisará exibir.

**URL:**

`GET https://www.hurb.com/search/api?q=gramado&page=1` (Obrigatória)

`GET https://www.hurb.com/search/api/suggestion?q=grama` (Opcional)

## Requisitos Mínimos
- Forkar esse desafio e criar o seu projeto (ou workspace) usando a sua versão desse repositório, tão logo acabe o desafio, submeta um *pull request*. Caso você tenha algum motivo para não submeter um *pull request*, ao término do desafio preencha o arquivo chamado `pull-request.txt`, comprima a pasta do projeto - incluindo a pasta `.git` - e nos envie por email.

- Faça chamadas assíncronas para a API e emita um log dos dados recebidos.

- Ler o resultado do JSON e colocar numa lista chamada `Hotels`.

- Exibir os itens de `Hotels` numa UITableView ou UICollectionView (como no iOS, por exemplo). Cada célula vai representar um ítem de `Hotels`e deve mostrar seu nome, preço, cidade, estado, uma foto  e três amenidades.

- Agrupar as células (usando um título para separá-las) pela quantidade de estrelas. Caso não exista estrelas, agrupe em *Pacotes*.
  - Deve estar ordena descrentemente pela quantidade de estrelas.

- Ter testes para o código criado


## Critério de avaliação

- **Organização do código**: Separação de módulos, view e model, back-end e front-end

- **Clareza**: O README explica de forma resumida qual é o problema e como pode rodar a aplicação?

- **Assertividade**: A aplicação está fazendo o que é esperado? Se tem algo faltando, o README explica o porquê?

- **Legibilidade do código** (incluindo comentários)

- **Segurança**: Existe alguma vulnerabilidade clara?

- **Cobertura de testes** (Não esperamos cobertura completa)

- **Histórico de commits** (estrutura e qualidade)

- **UX**: A interface é de fácil uso e auto-explicativa? A API é intuitiva?

- **Escolhas técnicas**: A escolha das bibliotecas, banco de dados, arquitetura, etc, é a melhor escolha para a aplicação?

- **"Algo a mais"**: Foi adicionada alguma feature não descrita nos requisitos mínimos? Ela foi bem executada? Traz alguma inovação interessante?

## Dúvidas

Quaisquer dúvidas que você venha a ter, consulte as [_issues_](https://github.com/HotelUrbano/challenge-alpha/issues) para ver se alguém já não a fez e caso você não ache sua resposta, abra você mesmo uma nova issue!

Boa sorte e boa viagem! ;)

**Exemplo da tabulação:**
**(Você pode alterar o layout conforme quiser)**

<table>
<tr>
<td>*3 estrelas*</td>
</tr>
<tr>
<td>
<table>
<tr><td>Barra Flat</td></tr>
<tr><td>Locação direto da Rio Apart Hoteis, não fazendo parte da administração da rede hoteleira que funciona no mesmo prédio. Troca de toalhas de 3 em 3 dias. Sem café da manhã incluso. Terá acesso normal a todas as áreas comuns do Prédio.</td></tr>
<tr><td>Barra Flat 2</td>`</tr>
<tr><td>Locação direto da Rio Apart Hoteis, não fazendo parte da administração da rede hoteleira que funciona no mesmo prédio. Troca de toalhas de 3 em 3 dias. Sem café da manhã incluso. Terá acesso normal a todas as áreas comuns do Prédio.</td></tr>
</table>
</td>
</tr>
<tr>
<td>*Pacotes*</td>
</tr>
<tr>
<td>
<table>
<tr><td>Pacote Rio de Janeiro: Carnaval 2018</td></tr>
<tr><td>O pacote&nbsp;inclui: &nbsp;

    Aéreo: Passagem aérea de ida e volta de São Paulo para Rio de Janeiro.

    Hospedagem: no Rio de Janeiro no Rios Presidente Hotel, Copacabana Mar Hotel, Augusto`s Copacabana Hotel ou similar (com café da manhã</td></tr>
</table>
</td>
</tr>
</table>


<p align="center">
  <img src="ca.jpg" alt="Challange accepted" />
</p>
