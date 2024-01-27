## Sobre o projeto

- Arquitetura: MVVM + Clean Architecture
- DI: Koin
  - Escolhi o Koin por sua simplicidade, facilidade de implementação, e por ter mais experiência com ele. O único ponto de atenção ao usar Koin é lembrar que se houver algum erro de injeção você só descobre durante runTime, o Hilt acusaria em compilationTime. Algo que pode ser remediado utilizando testes unitários para os módulos Koin.
- Network: Retrofit + OkHttp + Gson
- UI: Compose + Koil
