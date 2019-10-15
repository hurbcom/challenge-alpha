//
//  SearchParams.swift
//  Alpha
//
//  Created by Theo Mendes on 15/10/19.
//  Copyright © 2019 Hurb. All rights reserved.
//

import Alamofire

struct SearchParams: Encodable {
    var keyword: String?
    var page = 0
}

extension SearchParams {
    enum CodingKeys: String, CodingKey {
        case keyword = "q"
        case page
    }
}
