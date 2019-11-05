//
//  HurbOffers.swift
//  HurbAlpha
//
//  Created by Rodrigo Bukowitz on 05/11/19.
//  Copyright © 2019 Rodrigo Bukowitz. All rights reserved.
//

/// struct with all information from Hurb API get response
struct HurbOffers: Codable {
    /// variables that will be used in the aplication
    var sku: String
    var isHotel: Bool?
    var isPackage: Bool?
    var smallDescription: String?
}

/// extension to represent all coding keys
extension HurbOffers {
    enum CodingKeys: String, CodingKey {
        case sku
        case isHotel
        case isPackage
        case smallDescription
    }
}
