# Desafio da HURB

### Aplicativo para localizar hotéis/pacotes de viagem dada uma localização inserida através de um texto.

* Tela principal onde a funcionalidade de `buscar` está implementada e onde os resultados são listados:


![1o screenshot](https://user-images.githubusercontent.com/99778212/213345213-095ba1ca-d9ff-4872-a27c-5280792b1d69.png)
![2o screenshot](https://user-images.githubusercontent.com/99778212/213346244-fa611d70-33fc-4f25-a4d3-7bf5c213b9c7.png)
![Simulator Screen Shot - iPhone 14 Pro - 2023-01-19 at 00 10 07](https://user-images.githubusercontent.com/99778212/213346860-20a8ec32-3be6-4616-bd48-90e22726f9f5.png)

* Logo ao clicar na célula, o usuário é levado para a próxima tela, com valores, descrição completa, localização no mapa, de que tipo é o produto e sugestões para o usuário no final da tela.


![Simulator Screen Shot - iPhone 14 Pro - 2023-01-19 at 00 10 07-2](https://user-images.githubusercontent.com/99778212/213347172-6d971537-3c2a-4363-9d4d-1c28174c095b.png)
![Simulator Screen Shot - iPhone 14 Pro - 2023-01-19 at 00 14 43](https://user-images.githubusercontent.com/99778212/213347309-75a88093-33bc-4eb4-98c1-ef4f63c77622.png)
![Simulator Screen Shot - iPhone 14 Pro - 2023-01-19 at 00 14 43-2](https://user-images.githubusercontent.com/99778212/213347961-6748e496-3054-4de8-9333-49ef157c6062.png)

* Na tela de detalhe de pedidos o usuário pode selecionar para compartilhar o produto e ver sugestões do aplicativo.

![Simulator Screen Shot - iPhone 14 Pro - 2023-01-19 at 00 25 33](https://user-images.githubusercontent.com/99778212/213349215-75e810d4-1522-41f2-b15e-0cc1b27554ce.png)
![Simulator Screen Shot - iPhone 14 Pro - 2023-01-19 at 00 28 09](https://user-images.githubusercontent.com/99778212/213349237-76b5bc09-ab07-4b66-be97-60cf1e18656c.png)

* Na tela principal o usuário pode selecionar a lista de favoritos, lista de últimas visualizações ou algum produto.

![Simulator Screen Shot - iPhone 14 Pro - 2023-01-19 at 00 33 47](https://user-images.githubusercontent.com/99778212/213349601-4dc31df1-86f9-4a4c-a759-d3c17a18468f.png)
![Simulator Screen Shot - iPhone 14 Pro - 2023-01-19 at 00 33 40](https://user-images.githubusercontent.com/99778212/213349609-be0fc9f4-7797-4a23-a0ca-9cf8e2137b0d.png)


* A interface gráfica é intuitiva. Utilizei apenas uma library chamada [ScrollViewController](https://github.com/darrarski/ScrollViewController).

* A arquitetura do projeto é MVVM que, na minha concepção, é apropriada para esse projeto pois possibilita a segregação de responsabilidades e é de fácil manutenção.
