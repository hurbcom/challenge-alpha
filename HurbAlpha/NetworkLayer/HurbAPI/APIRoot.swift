//
//  HurbAPIRoot.swift
//  HurbAlpha
//
//  Created by Rodrigo Bukowitz on 05/11/19.
//  Copyright © 2019 Rodrigo Bukowitz. All rights reserved.
//

/// representes what the API return to the application
struct APIRoot: Codable {
    var results: [HurbOffers]
}

/// extension to represent all coding keys
extension APIRoot {
    enum CodingKeys: String, CodingKey {
        case results
    }
}
