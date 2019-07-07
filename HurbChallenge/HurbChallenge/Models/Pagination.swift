//
//  Pagination.swift
//  HurbChallenge
//
//  Created by Felipe Alves on 05/07/19.
//  Copyright © 2019 Bolzaniapps. All rights reserved.
//

import Foundation

struct Pagination: Codable {
    let count: Int?
    let firstPage: String?
    let nextPage: String?
    let previousPage: String?
    let lastPage: String?
    
    /**
     Retorna a URL da próxima página, corrigindo o host.
     
     - returns: A URL da próxima página, se houver, com o host correto
     
     Realizando testes com a API, reparei que o host das urls da paginação estão vindo incorretos, provavelmente com um endpoint legado.
     Reparei também que substituindo o host, as urls se comportam conforme o esperado.
     Logo, criei esse método para permitir corrigir as urls da próxima página de forma automática.
    */
    func fixedNextPageUrl() -> URL? {
        guard let nextPage = nextPage else { return nil }
        let fixedAddress = nextPage.replacingOccurrences(of: "http://searchapi/v2/search", with: "https://www.hurb.com/search/api").addingPercentEncoding(withAllowedCharacters: .urlQueryAllowed)!
        let url = URL(string: fixedAddress)
        return url
    }
}
