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
    // MARK: - Properties
    var currency: String?
    var oldPrice: Double?
    var amount: Double
    
    // MARK: - Methods

    /// Returns the price symbol according to the currency value
    func getPriceSymbol() -> String {
        switch self.currency {
        case "BRL":
            return "R$"
        case "USD":
            return "$"
        default:
            return "R$"
        }
    }
    
    // MARK: - Coding Keys
    enum CodingKeys: String, CodingKey {
        case currency
        case oldPrice = "old_price"
        case amount
    }
}
