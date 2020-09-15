//
//  Pagination.swift
//  DesafioHurb
//
//  Created by Edson Aparecido Guido on 13/09/20.
//  Copyright © 2020 Edson Aparecido Guido. All rights reserved.
//

import Foundation

struct Pagination: Codable {
    let count: Int
    let previousPage: String?
    var previousPageNumber: Int? {
        guard let previousPage = previousPage else { return nil }
        return parsePageNumber(url: previousPage)
    }
    let firstPage: String
    var firstPageNumber: Int? {
        return parsePageNumber(url: firstPage)
    }
    let nextPage: String?
    var nextPageNumber: Int? {
        guard let nextPage = nextPage else { return nil }
        return parsePageNumber(url: nextPage)
    }
    let lastPage: String
    var lastPageNumber: Int? {
        return parsePageNumber(url: lastPage)
    }

    private func parsePageNumber(url: String) -> Int? {
        guard let page = Int(url.deletingPrefix("http://searchapi/v2/search?q=buzios&page=")) else {
            return nil
        }
        return page
    }
}
