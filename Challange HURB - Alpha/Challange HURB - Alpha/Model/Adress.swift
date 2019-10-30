//
//  Adress.swift
//  Challange HURB - Alpha
//
//  Created by Luiz Fernando Cunha Duarte on 28/10/19.
//  Copyright © 2019 Luiz Fernando Cunha Duarte. All rights reserved.
//

import Foundation

/// Represents the main data of the Experience adress
struct Adress: Codable {
    var zipcode: String?
    var street: String?
    var city: String
    var state: String
    var country: String
    
    enum CodingKeys: String, CodingKey {
        case zipcode
        case street
        case city
        case state
        case country
    }
}
