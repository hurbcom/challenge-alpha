//
//  Price.swift
//  HurbAlpha
//
//  Created by Rodrigo Bukowitz on 06/11/19.
//  Copyright © 2019 Rodrigo Bukowitz. All rights reserved.
//

import Foundation

/// Price from the package or Hotel
struct Price: Codable {
    var oldPriceHotel: Float?
    var oldPricePackage: Float?
    // in the case of a Hotel
    var totalPrice: Float?
    
    // in the case of a Package
    var currentPrice: Float?
}
/// extension to represent all coding keys
extension Price {
    enum CodingKeys: String, CodingKey {
        case oldPriceHotel = "old_price"
        case oldPricePackage = "oldPrice"
        case totalPrice = "total_price"
        case currentPrice
    }
}
