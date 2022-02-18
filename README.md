# Challenge Alpha
[![CI](https://github.com/aka-godinez-2/challenge-alpha/actions/workflows/main.yml/badge.svg)](https://github.com/aka-godinez-2/challenge-alpha/actions/workflows/main.yml)

### Como abrir o projeto?
---
O projeto foi criado a partir do Android Studio Arctic Fox, então qualquer versão do mesmo igual ou superior à essa deve abrir sem maiores complicações. 
Para visualizar o projeto no android studio, importe o projeto à partir do arquivo `build.gradle` que se encontra na pasta `/android`

### Árvore de dependências entre os módulos do projeto
---
<p align="center">
  <img src="/android/project.dot.png" alt="Dependency Tree" />
</p>

### Principais features
---
- Listagem de hotéis a partir da API Rest
- Tela de detalhes de hotéis
- Últimos hotéis vistos

### Principais stacks tech
---
- [Coroutines + Flow](https://kotlinlang.org/docs/coroutines-guide.html) - Programação assíncrona com Kotlin
- [Detekt](https://detekt.dev/) - Análise estática para Kotlin
- [Firebase Distribution](https://firebase.google.com/docs/app-distribution) - Upload dos apks gerados a partir do CI
- [Gerenciamento de versão](https://github.com/aka-godinez-2/challenge-alpha/blob/master/android/current-version.txt) - Tags sendo gerenciadas por esse arquivo + Schedule do Github Action
- [Github Actions](https://github.com/features/actions) - CI/CD¹
- [Gradle Doctor](https://runningcode.github.io/gradle-doctor/) - Sugestões para melhoramento de builds com gradle²
- [Koin](https://insert-koin.io/) - Injeção de dependencia para Kotlin!
- [Kover](https://github.com/Kotlin/kotlinx-kover) + [Codecov](https://about.codecov.io/) - Coverage dos testes do código²
- [Leak Canary](https://square.github.io/leakcanary/) - Detecção de memory leaks
- [Robolectric](http://robolectric.org/) - Testes unitários em classes que utilizem framework do android
- [Room](https://developer.android.com/training/data-storage/room) - Database local com SQLite no android
- [Unused Resources Remover](https://github.com/konifar/gradle-unused-resources-remover-plugin) - Plugin do gradle para remover arquivos não utilizados do projeto

¹Tive um "pequeno-grande" problema com o CI. Basicamente fui banido do github por não saber que não podia usar os github actions (nos self-hosted runners do github) para algumas coisas, por isso criei essa conta "2". Depois de entrar em contato com o suporte, decidi comentar o código de alguns jobs (como fazer o upload pro codecov e abrir PR para remover os arquivos não utilizados) afim de evitar problemas novamente.

²Como o gradle doctor acaba adicionando uma camada extra de configuração para buildar o projeto, preferi comentar o código dele no momento final para vcs não terem grandes problemas ao rodar o projeto

### Informações adicionais
---
Divirtam-se =]

<p align="center">
  <img src="https://memegenerator.net/img/instances/55383699/challenge-completed.jpg" alt="Challenge Completed" />
</p>



