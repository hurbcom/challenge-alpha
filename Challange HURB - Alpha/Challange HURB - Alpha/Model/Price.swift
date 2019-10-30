//
//  Price.swift
//  Challange HURB - Alpha
//
//  Created by Luiz Fernando Cunha Duarte on 28/10/19.
//  Copyright © 2019 Luiz Fernando Cunha Duarte. All rights reserved.
//

import Foundation

/// Represent the price of an Experience, storing its actual price, old price and currency
struct Price: Codable {
    var currency: String
    var oldPrice: Double?
    var price: Double?

    /// Returns the price symbol according to the currency value
    func getPriceSymbol() -> String {
        switch self.currency {
        case "BRL":
            return "R$"
        default:
            return "$"
        }
    }
    
    enum CodingKeys: String, CodingKey {
        case currency
        case oldPrice
        case price
    }
}
