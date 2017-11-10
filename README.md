# <img src="https://avatars1.githubusercontent.com/u/7063040?v=4&s=200.jpg" alt="FrontEndBR" width="24" /> Desafio α

Crie um aplicativo para dispositivo móvel (**Objective-C**, **Swift**, **Kotlin**, **React Native**) que consuma o JSON exporto pela API de hotéis e o exiba usando uma _table view_. Você pode usar bibliotecas de terceiros para criar sua aplicação.


## API
A URL a seguir entrega a listagem de resultados para a cidade de Búzios como um documento JSON.

O documento contem um nó chamado `results` o qual tem os hotéis do Rio de Janeiro que você precisará exibir.

**URL:**
`GET https://www.hu.com.br/busca/api?q=Rio%20de%20Janeiro`

**Exemplo de resposta:**

```js
{
    "meta": {
        "count": 260,
        "offset": 0,
        "query": "Rio de Janeiro",
        "warning": "results.price.current_price and results.price.old_price are deprecated",
        "countWithAvailabilityInPage": 0,
        "responseTime": {
            "searchEngine": 937,
            "total": 1657
        },
        "countHotel": 237,
        "countPackage": 23
    },
    "results": [{
        "sku": "NHU-4623-0-0-0-0",
        "isHotel": true,
        "category": "hotel",
        "smallDescription": ".",
        "amenities": [{
                "name": "Quadra de tênis",
                "category": "Atividades"
            },
            {
                "name": "Piscina ao ar livre (o ano inteiro)",
                "category": "Piscina e comodidades de bem-estar"
            },
            {
                "name": "Sauna",
                "category": "Piscina e comodidades de bem-estar"
            },
            {
                "name": "Academia",
                "category": "Piscina e comodidades de bem-estar"
            },
            {
                "name": "Serviço diário de limpeza",
                "category": "Serviços de limpeza / Lavanderia"
            },
            {
                "name": "Recepção 24 horas",
                "category": "Serviços de recepção"
            },
            {
                "name": "Balcão de turismo",
                "category": "Serviços de recepção"
            },
            {
                "name": "Depósito de bagagens",
                "category": "Serviços de recepção"
            },
            {
                "name": "Business center",
                "category": "Comodidades / Instalações para negócios"
            },
            {
                "name": "Quartos não fumantes",
                "category": "Diversos"
            },
            {
                "name": "Ar-condicionado",
                "category": "Diversos"
            },
            {
                "name": "Jardim",
                "category": "Áreas comuns"
            },
            {
                "name": "Aparelho TV",
                "category": "Acomodação"
            },
            {
                "name": "Ar-Condicionado",
                "category": "Acomodação"
            },
            {
                "name": "Banheiro",
                "category": "Acomodação"
            },
            {
                "name": "Frigobar",
                "category": "Acomodação"
            },
            {
                "name": "Piso",
                "category": "Acomodação"
            },
            {
                "name": "Telefone",
                "category": "Acomodação"
            },
            {
                "name": "TV por cabo",
                "category": "Acomodação"
            }
        ],
        "id": "HU4623",
        "price": {
            "current_price": 155.84,
            "old_price": 300,
            "sku": "NHU-4623-0-0-0-0",
            "originalAmountPerDay": 300,
            "amountPerDay": 155.84,
            "amount": 155.84
        },
        "image": "https://d1wawz8va1fvss.cloudfront.net/reservas/prod0/4/4623/54fdde003ccb5_41762396.jpg",
        "name": "Barra Flat",
        "url": "https://www.hu.com.br/hoteis/rio-de-janeiro/barra-premium-4623",
        "description": "Locação direto da Rio Apart Hoteis, não fazendo parte da administração da rede hoteleira que funciona no mesmo prédio. Troca de toalhas de 3 em 3 dias. Sem café da manhã incluso. Terá acesso normal a todas as áreas comuns do Prédio.",
        "stars": 3,
        "gallery": [{
                "description": "A 750 m do shopping Rio Design",
                "url": "https://d1wawz8va1fvss.cloudfront.net/reservas/prod0/4/4623/54fdde003ccb5_41762396.jpg"
            },
            {
                "description": "",
                "url": "https://d1wawz8va1fvss.cloudfront.net/reservas/prod0/4/4623/54fdddece38af_41748326.jpg"
            },
            {
                "description": "",
                "url": "https://d1wawz8va1fvss.cloudfront.net/reservas/prod0/4/4623/54fdddf0a2fa2_41752452.jpg"
            },
            {
                "description": "",
                "url": "https://d1wawz8va1fvss.cloudfront.net/reservas/prod0/4/4623/5693c7b050bfd_barra-premium.JPG"
            },
            {
                "description": "",
                "url": "https://d1wawz8va1fvss.cloudfront.net/reservas/prod0/4/4623/5693c7b1b6538_barra-premium.JPG"
            },
            {
                "description": "",
                "url": "https://d1wawz8va1fvss.cloudfront.net/reservas/prod0/4/4623/5693c7b4975ef_barra-premium.JPG"
            }
        ],
        "address": {
            "city": "Rio de Janeiro",
            "country": "Brasil",
            "state": "Rio de Janeiro",
            "street": "Rua Malibu,",
            "zipcode": "22793-295",
            "geoLocation": {
                "lat": -23.0016013,
                "lon": -43.3920804
            }
        },
        "tags": [
            "reveillon",
            "Saldão 10 OFF",
            "Turismo Historico - Sudeste",
            "Pascoa - Sudeste",
            "Tiradentes - Sudeste",
            "Dia do Trabalho - Sudeste",
            "Corpus Christi - Sudeste",
            "Dia da Independência - Sudeste",
            "Dia das Crianças - Sudeste",
            "super-blacktrip"
        ],
        "quantityDescriptors": {
            "maxChildren": 0,
            "maxAdults": 2,
            "maxFreeChildrenAge": 0
        },
        "featuredItem": {
            "amenities": [
                "Aparelho TV",
                "Ar-Condicionado",
                "Banheiro",
                "Frigobar",
                "Internet WiFi",
                "Serviço de despertador",
                "Telefone",
                "TV por cabo",
                "Varanda / Sacada"
            ],
            "name": "Flat para duas pessoas - Sem café",
            "image": "https://d1wawz8va1fvss.cloudfront.net/reservas/prodaccommodation/13271/41749764.jpg",
            "description": "Flat de 32m² com varanda, ar-condicionado, TV LCD com canais a cabo e minibar. Sem café da manhã. Troca da roupa de banho de 3 em 3 dias."
        },
        "goodDates": [{
            "checkin": "2017-12-22",
            "checkout": "2017-12-26",
            "name": "Natal",
            "image": "https://d1wawz8va1fvss.cloudfront.net/reservas/prod/eventos/desktop/648/595fca1e93a1f_Natal-Desktop (1).jpg",
            "price": 722.08
        }]
    }]
}
```

## Requisitos
- Forkar esse desafio e criar o seu projeto (ou workspace) usando a sua versão desse repositório, tão logo acabe o desafio, submeta um *pull request*.
- Faça chamadas assíncronas para a API e emita um log dos dados recebidos.
- Ler o resultado do JSON e colocar numa lista chamada `Hotels`.
- Exibir os itens de `Hotels` numa UITableView ou UICollectionView (como no iOS, por exemplo). Cada célula vai representar um ítem de `Hotels`e deve mostrar seu nome, preço, cidade, estado, uma foto  e três amenidades.
- Agrupar as células (usando um título para separá-las) pela quantidade de estrelas. Caso não exista estrelas, agrupe em *Pacotes*.
  - Deve estar ordena descrentemente pela quantidade de estrelas.
- Ter testes para o código criado

## Dúvidas

Quaisquer dúvidas que você venha a ter, consulte as [/issues] para ver se alguém já não a fez e caso você não ache sua resposta, abra você mesmo uma nova issue!

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
