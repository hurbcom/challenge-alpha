# HURB iOS Challange
**Desenvolvido por:** Gáudio Uchôa Ney

**Contato**: gaudiouchoaney@gmail.com


## 1. CONTEÚDO
- [1. Conteúdo](#1-conteúdo)
- [2. Informações sobre o Projeto](#2-informações-sobre-o-projeto)
- [3. Requisitos mínimos](#4-requisitos-mínimos)
- [4. Instruções de Instalação](#3-instruções-de-instalação)
- [5. Tarefas requisitas e Bônus implementados](#5-tarefas-requisitadas-e_bônus-implementados)
- [6. Tarefas pendentes](#6-tarefas-pendentes)
- [7. Design e Resultado das Telas](7#-design-e-resultado-das-telas)
- [8. Testes](#8-teste)
- [9. Logs](#9-logs)
- [10. Dependências](#10-dependências)
- [10.1. CocoaPods](#10.1-ckocoaPods)
- [10.2 Kingfisher](#10.2-Kingfisher)
- [11. Vulnerabilidades](#11-vulnerabilidades)
    - [11.1. Imagens mockadas no JSON sem serem do tipo HTTPS](#11.1-imagens-mockadas-no-JSON-sem-serem-do-tipo-HTTPS)


## 2. INFORMAÇÕES SOBRE O PROJETO
- **Frameworks da Apple utilizados:** UIKit (viewCode) e Core Data.
- **Arquitetura utilizada:** MVVM
- **Formato de chamada escolhida:** REST (utilizando os mocks de JSON disponíveis na descrição do desafio)
- **Bibliotecas externas utilizadas:** KingFisher e Alamofire.
- **Gerenciador de dependêcias escolhido:** CocoaPods.


## 3. REQUISITOS MÍNIMOS
- **Target iOS:** 13.0
- **XCode:** 13+
- **Swift:** 5
- **Instalações necessárias:** [CocoaPods](https://cocoapods.org/)


## 4. INSTRUÇÕES DE INSTALAÇÃO
- Instale o CocoaPods na máquina.
- Abra o arquivo: Hurb_iOS_Challenge.xcworkspace
- Rode o simulado do projeto no XCode.


## 5. TAREFAS REQUISITADAS E BÔNUS IMPLEMENTADOS
- **Geral:**
    - ✅ Fazer chamadas assíncronas para a API.
    - ✅ Emitir um log dos dados recebidos. 
    - ✅ Ter testes para o código criado.
- **Obrigatórios REST:**
    - ✅ Ler o resultado do JSON e colocar numa lista chamada "Hotels".
    - ✅ Exibir os itens de Hotels numa UITableView ou UICollectionView (como no iOS, por exemplo). Cada célula vai representar um ítem de Hotelse deve mostrar seu nome, preço, cidade, estado, uma foto e três amenidades.
    - ✅ Agrupar as células (usando um título para separá-las) pela quantidade de estrelas. Caso não exista estrelas, agrupe em Pacotes. Veja os exemplos de resposta para hoteis e pacotes. (Deve estar ordena descrentemente pela quantidade de estrelas.)
- **Bônus:**
    - ✅ Último visto.


## 6. TAREFAS PENDENTES
- ⚠️ Separação dos Pacotes na Listagem.
- ⚠️ Correção de adição de mais de uma vez o mesmo hotel no "Últimos vistos"

## 7. DESIGN E RESULTADO DAS TELAS
Tendo em vista que o Design não tinha requisitos a serem seguidos, busquei implementar uma interface simples e objetiva, que atende as necessidads do projeto e ao escopo que delimitei para entregar o desafio. O Design trouxe maior destaque para as imagens em um carrossel na horizontal dentro de uma célula da listagem. 

Buscando dispor as informações de forma minimalista e clara, os textos como preço, título, localização etc. seguiram o padrão de fonte nativa iOS e o peso`.regular` e o `.light`.

Além disso, as cores utilizadas no projeto seguiram o padrão de [acessibilidade para daltonismo da ferramenta da Adobe](https://color.adobe.com/pt/create/color-accessibility).

É importante ressaltar que, na tela de `Últimos Vistos`, as células foram criadas com as informações disponíveis nas mocks do JSON e que poderiam ser salvas utilizando o `CoreData`.

Por fim, foram implementados alertas em caso de falhas e erros para dar um retorno ao usuário, além do tratamento para possíveis retornos vazios ou nulos das requests.

### Home
![Simulator Screen Shot - iPhone 11 - 2022-06-06 at 18 32 20](https://user-images.githubusercontent.com/62902650/172260738-9784998b-35fc-44e7-ab86-2fab632c99d9.png) ![Simulator Screen Shot - iPhone 11 - 2022-06-06 at 18 32 54](https://user-images.githubusercontent.com/62902650/172260751-b124bf53-c699-4118-9cb3-c4d3d6fd2c1a.png)

### Favotitos (não implementado devido a redefinição de escopo)
![Simulator Screen Shot - iPhone 11 - 2022-06-06 at 18 33 02](https://user-images.githubusercontent.com/62902650/172260796-b65f3325-e001-43fa-9bb6-cce59a1eee8f.png)

### Detalhe do Hotel
![Simulator Screen Shot - iPhone 11 - 2022-06-06 at 18 34 22](https://user-images.githubusercontent.com/62902650/172260867-ca641b4e-0e38-4c20-a3b4-6848ecd14df4.png) ![Simulator Screen Shot - iPhone 11 - 2022-06-06 at 18 34 35](https://user-images.githubusercontent.com/62902650/172260879-e8c25a44-f642-4904-990b-a315e85e3fd7.png)

### Últimos Vistos
![Simulator Screen Shot - iPhone 11 - 2022-06-06 at 18 34 13](https://user-images.githubusercontent.com/62902650/172260953-a2b090d5-686d-4c61-b73c-99e5bb4e8d80.png) ![Simulator Screen Shot - iPhone 11 - 2022-06-06 at 18 33 16](https://user-images.githubusercontent.com/62902650/172260986-d95c8b4b-1cae-425f-9c64-9a5aef9b37a3.png)

## 8. TESTES
Pessoalmente, confesso que, durante minha trajetória como desenvolvedor iOS, eu infelizmente tive pouco contato com testes automatizados ou trabalhei em projetos que aplicassem o Test-Driven Development(TDD). Apesar disso, busquei mostrar o mínimo que sei sobre o assunto e o que achei pertinente testar dentro de um escopo fechado e de forma bem simples. Para isso, foram testados apenas alguns retornos de variáveis tradas na `ViewModel` das células da `Home`.

Como pode ser observado nos commmits, os testes foram implementados em um momento pós produção, o que não é uma boa prática, mas que só fui descobrir isso ao estudar mais sobre o assunto para o desafio. Apesar de ter entregado o mínimo requerido para o início dos testes do projeto, foi o melhor que consegui desenvolver tendo em vista o escopo do projeto e os meus conhecimentos sobre o assunto.

## 9. LOGS
Levando em consideração as poucas chamadas existentes no projeto, optei por imprimir os resultados das requisições REST no próprio Console do XCode. 
Foram cobertos os _Status_ de ✅ `.success` ou ❌ `.failure`. 

## 10. DEPENDÊNCIAS
   - **Alamorire:** Por ser uma biblioteca pensada para a linguagem de programação Swift e por ser amplamente utilizada para realizar requisições HTTP para as plataformas iOS, macOS, o Alamofire foi utilizado para acessar o arquivo JSON local e realizar retornar o um resultado mais prático de se tratar.

   - **Kingfisher:** É um ótimo gerenciador de imagens e a principal vantagem dele é a possibilidade de fazer cash das imagens, diminuindo assim o número de resições necessárias para a implementção das células e até a própria troca de imagens ao ser aplicada a reutiliação de células do próprio `Swift`.

## 11. VULNERABILIDADES
- Foi observado que alguma imagens estavam retornando URLs sem o protocolo de segurança HTTPS, ou seja, vinham em HTTP. Realizei alguns tentes e elas funcionaram ao forçar o acesso através do protocolo HTTPS também, mas é importante ressaltar que essa não é uma boa prática e que as informações da base de dados precisam vir do serviço já tratando essa questão de segurança. 

