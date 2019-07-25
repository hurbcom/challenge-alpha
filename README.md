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

**Exemplo de resposta:**

```js
{
    "meta": {
        "count": 193,
        "offset": 0,
        "query": "gramado",
        "warning": "results.price.current_price and results.price.old_price are deprecated",
        "countWithAvailabilityInPage": 0,
        "countHotel": 143,
        "countPackage": 24,
        "countTicket": 26,
        "countBustrip": 0,
        "countDisney": 0
    },
    "filters": {
        "amenities": [
            {
                "term": "Academia de ginástica gratuita",
                "filter": "amenity_academia_de_ginastica_gratuita|1",
                "count": 28
            }
        ],
        "attributes": [
            {
                "term": "Acessibilidade",
                "filter": "attribute_acessibilidade|1",
                "count": 3
            }
        ],
        "countries": [
            {
                "term": "Brasil",
                "filter": "country_brasil|1",
                "count": 193
            }
        ],
        "cities": [
            {
                "term": "Campos dos Goytacazes",
                "filter": "city_campos_dos_goytacazes|1,state_rio_de_janeiro|1",
                "count": 1
            }
        ],
        "departureCities": [
            {
                "term": "Aracaju",
                "filter": "departurecity_aracaju|1",
                "count": 1
            }
        ],
        "district": [
            {
                "term": "Alpes Verdes, Canela",
                "filter": "neighborhood_alpes_verdes|1,city_canela|1",
                "count": 1
            }
        ],
        "neighborhood": [
            {
                "term": "Alpes Verdes, Canela",
                "filter": "neighborhood_alpes_verdes|1,city_canela|1",
                "count": 1
            }
        ],
        "duration": [
            {
                "term": "1 diária",
                "filter": "duration_1_diaria|1",
                "count": 26
            }
        ],
        "food": [
            {
                "term": "Almoço",
                "filter": "food_almoco|1",
                "count": 1
            }
        ],
        "people": [
            {
                "term": "1",
                "filter": "total_people_1|1",
                "count": 49
            }
        ],
        "prices": [
            {
                "min": 0,
                "maxExclusive": 300,
                "filter": "pricegroup_1|1",
                "count": 120
            }
        ],
        "priceInterval": {
            "min": 1800,
            "max": 300300,
            "filterPattern": "price_[min|max]_[value]"
        },
        "productType": [
            {
                "term": "hospedagem",
                "filter": "product_type_hospedagem|1",
                "count": 24
            }
        ],
        "regulation": [
            {
                "term": "accessibility_no",
                "filter": "regulation_accessibility_no|1",
                "count": 50
            }
        ],
        "rooms": [
            {
                "term": "Standard",
                "filter": "room_standard|1",
                "count": 13
            }
        ],
        "stars": [
            {
                "term": "1",
                "filter": "stars_1|1",
                "count": 1
            }
        ],
        "states": [
            {
                "term": "Rio de Janeiro",
                "filter": "state_rio_de_janeiro|1,country_brasil|1",
                "count": 2
            }
        ]
    },
    "results": [
        {
            "sku": "OMN-2762-0-0-0-0-0-0",
            "isHotel": true,
            "category": "hotel",
            "smallDescription": "O Klein Ville Gramado te espera com uma ótima estrutura projetada para oferecer conforto e tranquilidade. O hotel possui a sua disposição uma equipe treinada e capacitada para bem atendê-lo. Todos os apartamentos são equipados com internet, telefone, TV a",
            "amenities": [
                {
                    "name": "Sala de tv",
                    "category": "Áreas comuns"
                },
                {
                    "name": "Recepção 24 horas",
                    "category": "Serviços de recepção"
                },
                {
                    "name": "Estacionamento",
                    "category": "Opções de transporte"
                },
                {
                    "name": "Estacionamento Gratuito",
                    "category": "Opções de transporte"
                }
            ],
            "id": "AT648",
            "price": {
                "currency": "BRL",
                "currency_original": "BRL",
                "current_price": 194.04,
                "old_price": 194.04,
                "sku": "OMN-2762-0-0-0-0-0-0",
                "originalAmountPerDay": 194.04,
                "amountPerDay": 194.04,
                "amount": 194.04
            },
            "hu_free_cancellation": true,
            "image": "https://novo-hu.s3.amazonaws.com/reservas/ota/prod/hotel/648/hotel-klein-ville-gramado-001_20181127120123_20190625175541.jpg",
            "name": "Hotel Klein Ville Gramado",
            "url": "https://www.hurb.com/hoteis/gramado/hotel-klein-ville-gramado-OMN-2762",
            "description": "O Hotel Klein Ville Gramado te espera com uma ótima estrutura projetada para oferecer conforto e tranquilidade. O hotel possui a sua disposição uma equipe treinada e capacitada para bem atendê-lo. Todos os apartamentos são equipados com internet, telefone, TV a Cabo, ar condicionado, cama box, frigobar, cofre e mesa de trabalho, além de uma decoração contemporânea e charmosa. Além de todo o conforto será possível desfrutar de uma farto e delicioso café da manhã, estilo colonial. No Hotel Klein Ville Gramado \" entre como hóspede e saia como amigo!\" \r\nQuer viajar em grupo? o HU te leva! Grupos a partir de 8 quartos podem solicitar cotações especiais! Basta entrar em contato pelo email grupohu@hotelurbano.com.br  ",
            "stars": 4,
            "gallery": [
                {
                    "description": "",
                    "url": "https://novo-hu.s3.amazonaws.com/reservas/ota/prod/hotel/648/hotel-klein-ville-gramado-001_20181127120123_20190625175541.jpg"
                },
                {
                    "description": "",
                    "url": "http://media.omnibees.com/Images/2762/Property/125224.jpg"
                },
                {
                    "description": "",
                    "url": "http://media.omnibees.com/Images/2762/Property/306273.jpg"
                },
                {
                    "description": "",
                    "url": "http://media.omnibees.com/Images/2762/Property/306274.jpg"
                },
                {
                    "description": "",
                    "url": "http://media.omnibees.com/Images/2762/Property/306275.jpg"
                }
            ],
            "address": {
                "city": "Gramado",
                "country": "Brasil",
                "id_atlas_city": null,
                "id_atlas_country": null,
                "id_atlas_neighborhood": null,
                "id_atlas_state": null,
                "id_city": 430910,
                "id_country": 1,
                "id_state": 43,
                "state": "Rio Grande do Sul",
                "street": "Rua Itapeva, 59 Carniel",
                "zipcode": "95670000",
                "geoLocation": {
                    "lat": -29.3596688,
                    "lon": -50.8557461
                }
            },
            "tags": [],
            "quantityDescriptors": {
                "maxChildren": 10,
                "maxAdults": 10,
                "maxFreeChildrenAge": 17
            },
            "featuredItem": {
                "amenities": [],
                "name": "LUXO",
                "image": "http://media.omnibees.com/Images/2762/RoomTypes/306260.jpg",
                "description": "- Ar condicionado; \r- Mini bar; \r- Secador de cabelo; \r- Cofre; \r- Wi-fi grátis; \r- TV a cabo; "
            }
        }
    ],
    "pagination": {
        "count": 10,
        "firstPage": "http://searchapi/v2/search?q=gramado&page=1",
        "nextPage": "http://searchapi/v2/search?q=gramado&page=2",
        "previousPage": null,
        "lastPage": "http://searchapi/v2/search?q=gramado&page=10"
    }
}
```

## Requisitos Mínimos
- Forkar esse desafio e criar o seu projeto (ou workspace) usando a sua versão desse repositório, tão logo acabe o desafio, submeta um *pull request*.

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
