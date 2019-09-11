//
//  ResultPrice.swift
//  HurbAlpha
//
//  Created by Julia Rocha on 04/09/19.
//  Copyright © 2019 Julia Rocha. All rights reserved.
//

import Foundation

// MARK: - Declaration

struct ResultPrice: Codable {
    let currency, currencyOriginal: String?
    let priceCurrentPrice, priceOldPrice: Double?
    let sku: String
    let originalAmountPerDay: Double?
    let amountPerDay, amount: Double
    let oldPrice, currentPrice, originalAmount: Int?
    
    // MARK: - Enum Coding Keys Declaration

    enum CodingKeys: String, CodingKey {
        case currency
        case currencyOriginal
        case priceCurrentPrice
        case priceOldPrice
        case sku, originalAmountPerDay, amountPerDay, amount, oldPrice, currentPrice, originalAmount
    }
}

// MARK: - Price Interval Declaration

struct PriceInterval: Codable {
    let min, max: Int
    let filterPattern: String
}

// MARK: - Price Element Declaration

struct PriceElement: Codable {
    let min, maxExclusive: Int
    let filter: String
    let count: Int
}


