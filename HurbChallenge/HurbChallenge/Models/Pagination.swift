//
//  Pagination.swift
//  HurbChallenge
//
//  Created by Felipe Alves on 05/07/19.
//  Copyright © 2019 Bolzaniapps. All rights reserved.
//

import Foundation

struct Pagination: Codable {
    let count: Int
    let firstPage: String?
    let nextPage: String?
    let previousPage: String?
    let lastPage: String?
    
    func fixedNextPageUrl() -> URL? {
        guard let nextPage = nextPage else { return nil }
        let fixedAddress = nextPage.replacingOccurrences(of: "http://searchapi/v2/search", with: "https://www.hurb.com/search/api").addingPercentEncoding(withAllowedCharacters: .urlQueryAllowed)!
        let url = URL(string: fixedAddress)
        return url
    }
}
