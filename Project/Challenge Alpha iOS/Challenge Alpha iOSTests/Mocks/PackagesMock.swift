//
//  PackagesMock.swift
//  Challenge Alpha iOSTests
//
//  Created by Yuri Strack on 19/01/23.
//

import Foundation
@testable import Challenge_Alpha_iOS

let mockedPackages: [PackageResult] = [
    .init(id: "1",
          sku: "LGPKG-1094980-0",
          name: "Pacote Rio de Janeiro",
          description: "\r\n\tA&eacute;reo:&nbsp;Passagem a&eacute;rea de ida e volta entre a cidade de origem e&nbsp;Rio de Janeiro&nbsp;- Aeroporto Internacional Tom Jobim -&nbsp;Gale&atilde;o (GIG)&nbsp;ou&nbsp;o Aeroporto Santos-Dumont (SDU), em classe econ&ocirc;mica,&nbsp;podendo haver conex&atilde;o e/ou escala.\r\n\tBagagem:&nbsp;Este pacote permite levar uma bagagem de m&atilde;o com at&eacute; 10 quilos. Desta forma, voc&ecirc; poder&aacute; levar uma mochila ou bolsa (que dever&aacute; ser acomodada debaixo do seu assento) e uma bagagem de m&atilde;o (que dever&aacute; caber no compartimento superior do avi&atilde;o).\r\n\t\r\n\tHospedagem:&nbsp;Em&nbsp;Rio de Janeiro&nbsp;no&nbsp;Hotel Atl&acirc;ntico Tower, Hotel Atl&acirc;ntico Prime, Hotel Bandeirantes&nbsp;ou outro&nbsp;de categoria&nbsp;econ&ocirc;mica, com caf&eacute; da manh&atilde;.\r\n\r\n\tA hospedagem ser&aacute; definida pelo Hurb de acordo com a disponibilidade e tarif&aacute;rio promocional. Se desejar um estabelecimento espec&iacute;fico, poder&aacute; solicit&aacute;-lo no campo&nbsp;&quot;Solicita&ccedil;&otilde;es sobre sua viagem&quot;&nbsp;do formul&aacute;rio e verificaremos o custo adicional.\r\n\r\n\tAten&ccedil;&atilde;o:&nbsp;As di&aacute;rias s&atilde;o contabilizadas pelas noites dormidas a partir da sua chegada no hotel.\r\n\r\n\tIdiomas do hotel:&nbsp;Portugu&ecirc;s, ingl&ecirc;s ou espanhol\r\n\t\r\n\r\n",
          category: "package",
          url: "",
          startDate: "2021-03-01T00:00:00",
          endDate: "2021-05-10T23:59:59",
          isAvailable: true,
          quantityDescriptors: .init(nights: 2,
                                     duration: 3,
                                     maxPeople: 1,
                                     maxFreeChildrenAge: nil),
          price: .init(amount: 699,
                       originalAmount: 699,
                       currency: "BRL",
                       taxes: []),
          address: .init(state: "Rio de Janeiro",
                         city: "Rio de Janeiro",
                         country: "Brasil",
                         geolocation: .init(latitude: -22.920158299821324, longitude: -43.4579963721119)),
          gallery: [
            .init(url: "https://thumbcdn-e.hotelurbano.net/hobxsQEe3e_2Otv_ed18T_E9afs=/origxorig/center/middle/filters:quality(70)/https://s3.amazonaws.com/legado-prod/prod/ofertas/imagens/2021/03/01/12/10/pacote_rio_de_janeiro_cristo_redentor_1044x696__1_.png",
                  description: nil
                 )
          ],
          amenities: [
            .init(name: "Apartamento", category: "Acomodações"),
            .init(name: "Café da manhã", category: "Alimentação"),
            .init(name: "Passagem aérea", category: "Passagem aérea"),
          ],
          tags: []
         )
]
