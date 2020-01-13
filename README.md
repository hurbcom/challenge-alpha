

## Challenge-Alpha Hurb

Projeto feito em resposta ao desafio Alpha da Hurb. A idéia do projeto é desenvolver um aplicativo que utilize a API disponibilizada pela Hurb para retornar ao usuário os valores da busca feita no aplicativo. Entre outros requisitos mínimos, o resultado da busca
deve retornar em ordem descrescente em classificação (estrelas), e agrupado de acordo com a classificação.

## Como utilizar

A forma mais fácil para rodar a aplicação, é clonar/download/forkar esse repositório e com Android Studio instalar o aplicativo em um emulador ou aparelho físico.

## O que foi adicionado ao desafio

Além dos requisitos mínimos, a aplicação foi desenvolvida com algumas funcionalidades extras.

-   Os usuários tem a opção de favoritar os hotéis e/ou pacotes.
-   É possível compartilhar os hotéis e/ou pacotes.
-   No fragmento inicial os usuários tem o histórico de visualizações em ordem descrescente de visita.
-   Também no fragmento inicial os usuários tem uma lista de últimas buscas que apresenta uma lista randômica de entradas de acordo com o histórico de buscas do usuário.
-   A partir de qualquer tela o usuário pode acessar os hotéis/pacotes com mais detalhes clicando na view.
-   A tela pode ser rotacionada em qualquer fragmento sem perda de informação
-   Como sugerido no desafio, ao digitar uma busca, a partir do terceiro caracter, o aplicativo retorna uma lista de sugestões de busca de acordo com o retorno da Api.
-   Embora não seja possível fazer buscas offline, o usuário tem acesso aos últimos vistos, últimas buscas e favoritos.

## Bibliotecas de destaque

-   **Jetpack Navigations**
-   **ViewModel**
-   **Kotlin Coroutines**
-   **Room**
-   **Dagger2**
-   **Assisted inject**
-   **Retrofit2**
-   **Glide**


## O que ficou a desejar

-   Os testes estão incompletos. Com apenas 6 meses de carreira na área, os 10 dias não foram o suficiente para adicionar todos os teste que eu gostaria.
-   O design ficou usável mas acredito que pode melhorar muito.
-   A solução não tem scroll infinito nos resultados. Acredito que é um feature obrigatória em um aplicativo do tipo, no entanto não encontrei uma forma visual agradável de manter o agrupamento das entradas e ao mesmo tempo fazer o load de mais conteúdo.
-   O fragmento com os detalhes dos hotéis/pacote está com poucos detalhes sobre o produto, embora toda informação passada pela API esteja disponível.
-   Faltou adicionar um filtro no fragmento de resultados. Acredito ser uma funcionalidade indispensável, no entanto, não descobri o comando para a API retornar valores filtrados e o filtro acabaria sem muita funcionalidade.
-   Faltou tratamento de erro para localidades diferentes da de Buzios. Por exemplo, Japão retorna muitas entradas sem cidade, entre outras que eu não tive tempo de observar.
-   Por falta de suporte do Room a alguns tipos de objeto, tive que fazer uma relation no banco para facilitar a busca de dados, no entanto, pela limitação do Room, não era possível manter a relation e utilizar mais de uma chave primária nas tabelas. 
Por isso, optei por criar 3 bancos distintos para salvar resultados/últimas buscas, favoritos, últimos vistos, o que poderia ser reduzido caso fosse possível utilizar mais de uma chave primária, garantindo a não repetição de entradas.


## Notas finais

Muito obrigado pela oportunidade de participar do processo/desafio. Foi muito divertido explorar um pouco da Api que vocês disponibilizaram e um pouco das nuances do negócio.

Como eu mencionei anteriormente, não tenho muita experiência na área, e por isso, ficaria extremamente grato se esse processo tiver um feedback quanto ao código - me dediquei bastante ao processo, e embora tenha feito o meu melhor, acredito que alguns pontos
possam ser melhorados, ainda que, eu não saiba com precisão quais.


