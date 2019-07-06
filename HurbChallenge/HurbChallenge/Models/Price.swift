//
//  Price.swift
//  HurbChallenge
//
//  Created by Felipe Alves on 05/07/19.
//  Copyright © 2019 Bolzaniapps. All rights reserved.
//

import Foundation

struct Price: Codable {
    let currency: String
    let currencyOriginal: String
    let currentPrice: Double
    let oldPrice: Double
    let sku: String
    let originalAmountPerDay: Double
    let amountPerDay: Double
    let amount: Double
    
    enum CodingKeys: String, CodingKey {
        case currency
        case currencyOriginal = "currency_original"
        case currentPrice = "current_price"
        case oldPrice = "old_price"
        case sku
        case originalAmountPerDay
        case amountPerDay
        case amount
    }
}
