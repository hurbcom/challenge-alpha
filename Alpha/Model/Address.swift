//
//  Address.swift
//  Alpha
//
//  Created by Theo Mendes on 19/10/19.
//  Copyright © 2019 Hurb. All rights reserved.
//

struct Address: Codable {
    var city: String
    var country: String
    var state: String
    var street: String?
    var zipcode: String?

    enum CodingKeys: String, CodingKey {
        case city
        case country
        case state
        case street
        case zipcode
    }
}
