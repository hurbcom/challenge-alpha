# Pedro Resende da Silva #

## Meu Desafio Android da hurb

 * a ui foi pensada com base na guideline do material3
 * usei arquiterura mvvm com jetpack compose para construir a ui
 * usei retrofit para chamar a api e room para persistencia local
 * usei koin para injeção de dependencia
 * Fiz alguns test cases

#### Feature a mais!
 * o app foi pensado no princípio offline first, sempre que uma consulta é feita na api, o dado é
salvo no banco de dados local
   * para consultas das listas paginadas, primeiro verifica se a categoria está "UP-TO-DATE",
se estiver, consulta a lista inteira no banco de dados local, se não, consulta a api para abastecer
o banco, quando chegar na última pagina da lista, a flag "UP-TO-DATE" para aquela categoria é marcada
como true
   * para consultas indíviduais, primeiro verifica se a categoria está "UP-TO-DATE",se não, verifica
se existe algum item com aquele id no banco, e em último caso, é consultada a api para pegar e salvar
o item.

#### Plus!
 * fiz os plus sugerido no desafio, listar items das categorias que foram salvos como favoritos
e vistos por ultimo(nos ultimos dois dias)

#### Easter egg
 * A logo da toolbar na home é dinamica, caso use o app no darm mode, será a logo do império, e caso
use no light mode, será a logo do rebeldes.

# Espero que gostem <3