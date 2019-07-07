# HURB CHALLENGE

## O que é?

Um aplicativo simples para iOS desenvolvido em Swift 5 e que exibe uma lista de Hotéis/Tickets/Pacotes a partir de uma consulta à API do Hurb.

## Como funciona?

Ao abrir o aplicativo, uma busca inicial pelo termo **Rio de Janeiro** é executada automaticamente. Deslizando-se o dedo para baixo, aparece uma barra de busca no topo, permitindo-se realizar uma busca por qualquer outro termo desejado. Ao finalizar sua busca customizada e apertar em *Cancelar*, é novamente realizada uma busca automática por **Rio de Janeiro** e a listagem é atualizada. A lista de resultados é paginada, carregando as páginas seguintes conforme o usuário se aproxima do final. Não há uma tela de detalhes para cada resultado, apenas a listagem. Os items estão separados nas seguintes seções: **5 Estrelas**, **4 Estrelas**, **3 Estrelas**, **2 Estrelas**, **1 Estrela**, **Tickets** e **Pacotes**. 

*Importante:* Não está no escopo do projeto a sanitização do termo de busca. Foi assumido que o servidor já realiza um tratamento para evitar buscas perigosas ou injeção de código.

## Como foi feito?

Foi utilizada a linguagem Swift 5, no XCode 10.2.1, tendo como deployment target o iOS 12.2 (a mais recente estável no momento da criação do projeto).
Como gerenciamento de dependências, foi utilizado o **CocoaPods** versão 1.7.3. 
As dependências externas definidas no *Podfile* são as seguintes:

• Google Promises versão 1.2.8 (https://github.com/google/promises)
    
    É utilizada para simplificar os fluxos de dados e melhorar substancialmente a legibilidade, compreensão e execução de código assíncrono, tal qual consultas à API.

• SDWebImage versão 5.0.3 (https://github.com/SDWebImage/SDWebImage)

    É utilizada para renderizar e realizar o cache local de imagens provenientes da web.

Detalhes de implementação e decisões de arquitetura podem ser encontrados na documentação do próprio código. Apenas uma classe recebeu testes unitários.