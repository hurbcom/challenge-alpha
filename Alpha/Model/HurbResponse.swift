//
//  APIRes.swift
//  Alpha
//
//  Created by Theo Mendes on 15/10/19.
//  Copyright © 2019 Hurb. All rights reserved.
//

import Foundation

/// Is the top-level of the API's response
struct HurbResponse: Codable {
    var results: [Deal]

    enum CodingKeys: String, CodingKey {
        case results
    }
}
