//
//  ResultPrice.swift
//  HurbAlpha
//
//  Created by Julia Rocha on 04/09/19.
//  Copyright © 2019 Julia Rocha. All rights reserved.
//

import Foundation

// MARK: - ResultPrice
class ResultPrice: Codable {
    let currency, currencyOriginal: String?
    let priceCurrentPrice, priceOldPrice: Double?
    let sku: String
    let originalAmountPerDay: Double?
    let amountPerDay, amount: Double
    let oldPrice, currentPrice, originalAmount: Int?
    
    enum CodingKeys: String, CodingKey {
        case currency
        case currencyOriginal
        case priceCurrentPrice
        case priceOldPrice
        case sku, originalAmountPerDay, amountPerDay, amount, oldPrice, currentPrice, originalAmount
    }
    
    init(currency: String?, currencyOriginal: String?, priceCurrentPrice: Double?, priceOldPrice: Double?, sku: String, originalAmountPerDay: Double?, amountPerDay: Double, amount: Double, oldPrice: Int?, currentPrice: Int?, originalAmount: Int?) {
        self.currency = currency
        self.currencyOriginal = currencyOriginal
        self.priceCurrentPrice = priceCurrentPrice
        self.priceOldPrice = priceOldPrice
        self.sku = sku
        self.originalAmountPerDay = originalAmountPerDay
        self.amountPerDay = amountPerDay
        self.amount = amount
        self.oldPrice = oldPrice
        self.currentPrice = currentPrice
        self.originalAmount = originalAmount
    }
    
}


