Para rodar a aplicação:
-Clonar o repositório;
-Instalar o cocoapods caso necessário;
-executar no prompt pod install

Informações sobre a aplicação;
-Arquitetura MVVM;
-RXSwift para bindings;
-Alamofire para conexão com a API;
-KingFisher para as imagens;
-IGListKit para a CollectionView;
-Implementada a consulta paginada view;

Problemas conhecidos:
-Imagens dos pacotes não esta aparecendo devido a um erro decoder do JSON;
-A ordenação por número de estrelas do hotel não funciona adequadamente devido ao endpoint não retornar os objetos com essa ordenação,
Caso o endpoint tenha essa opção funcionará corretamente;