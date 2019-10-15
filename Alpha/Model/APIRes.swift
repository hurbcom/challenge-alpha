//
//  APIRes.swift
//  Alpha
//
//  Created by Theo Mendes on 15/10/19.
//  Copyright © 2019 Hurb. All rights reserved.
//

import Foundation

struct APIRes: Codable {
    var results: [APIResult]

    enum CodingKeys: String, CodingKey {
        case results
    }
}
